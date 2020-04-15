package ticketbooking.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ticketbooking.model.Order;
import ticketbooking.model.Screening;
import ticketbooking.model.Seat;
import ticketbooking.model.Ticket;
import ticketbooking.service.ClientService;
import ticketbooking.service.AppUserService;
import ticketbooking.service.OrderService;
import ticketbooking.service.ScreeningService;
import ticketbooking.service.SeatService;
import ticketbooking.service.TicketService;
import ticketbooking.struct.TicketType;
import ticketbooking.web.CreateOrderRequest;
import ticketbooking.web.CreateTicketRequest;
import ticketbooking.web.OrderRequest;
import ticketbooking.web.OrderResponse;
import ticketbooking.web.exception.ScreeningTimeException;
import ticketbooking.web.exception.SeatException;
import ticketbooking.web.exception.SeatScreeningException;
import ticketbooking.web.exception.SeatDuplicateException;
import ticketbooking.web.exception.OrderException;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    private AppUserService appUserService;

    private ClientService clientService;

    private ScreeningService screeningService;

    private SeatService seatService;

    private TicketService ticketService;

    public OrderController(OrderService orderService, AppUserService appUserService, ClientService clientService, ScreeningService screeningService, SeatService seatService, TicketService ticketService) {
        this.orderService = orderService;
        this.appUserService = appUserService;
        this.clientService = clientService;
        this.screeningService = screeningService;
        this.seatService = seatService;
        this.ticketService = ticketService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody CreateOrderRequest cor, @RequestParam(required = false) String voucher) {

        Long screeningId = cor.getScreeningId();
        if (!screeningService.checkIfAvailableForBooking(screeningId)) {
            throw new ScreeningTimeException();
        }

        List<CreateTicketRequest> tickets = cor.getTickets();
        List<Long> seatsIds = tickets.stream()
                .map((a) -> a.getSeatId())
                .collect(Collectors.toList());

        //Seats ids should not duplicate.
        Set tempSet = new HashSet();
        for (Long seatId : seatsIds) {
            if (!tempSet.add(seatId)) {
                throw new SeatDuplicateException();
            }
        }

        if (!seatService.checkIfAvailableForBooking(screeningId, seatsIds)) {
            throw new SeatScreeningException();
        }

        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < seatsIds.size(); i++) {
            seats.add(seatService.find(seatsIds.get(i)));
        }
        if (!seatService.checkIfAvailableForBooking(seats)) {
            throw new SeatException();
        }

        Screening screening = screeningService.find(screeningId);

        //Order for seats from different room than screening should not be possible.
        for (Seat seat : seats) {
            if (!seat.getRoom().getId().equals(screening.getRoom().getId())) {
                throw new OrderException();
            }
        }

        Order order = new Order(cor.getTitle(), cor.getDescription(), cor.getPaymentType(), cor.getType(), appUserService.find(cor.getAppUserId()),
                clientService.find(cor.getClientId()), screening);
        order.setSeats(seats);
        order = orderService.save(order);

        boolean isPromotion = false;
        if (voucher != null) {
            if (ticketService.checkVoucherCode(voucher)) {
                isPromotion = true;
            }
            for (CreateTicketRequest ticket : tickets) {
                BigDecimal price = ticketService.calculateTicketPrice(screeningId, ticket.getType(), voucher);
                Seat seat = seatService.find(ticket.getSeatId());
                ticketService.save(new Ticket(ticket.getType(), isPromotion, price, screening,
                        seat, order));
            }
        } else {
            for (CreateTicketRequest ticket : tickets) {
                BigDecimal price = ticketService.calculateTicketPrice(screeningId, ticket.getType());
                Seat seat = seatService.find(ticket.getSeatId());
                ticketService.save(new Ticket(ticket.getType(), false, price, screening,
                        seat, order));
            }
        }
        LOGGER.info("Order created.");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse read(@PathVariable Long id) {

        Order order = orderService.find(id);
        List<Seat> seats = order.getSeats();
        List<Long> seatsIds = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            seatsIds.add(seats.get(i).getId());
        }
        List<Ticket> tickets = order.getTickets();
        List<Long> ticketsIds = new ArrayList<>();
        for (int i = 0; i < tickets.size(); i++) {
            ticketsIds.add(tickets.get(i).getId());
        }
        return new OrderResponse(order.getId(), order.getTitle(), order.getDescription(), order.getPaymentType(), order.getType(), order.getOrderDate(),
                order.getAppUser().getId(), order.getClient().getId(), order.getScreening().getId(), seatsIds, ticketsIds);
    }

    @GetMapping(value = "/ticketPrice")
    public BigDecimal getTicketPrice(@RequestParam Long screeningId, @RequestParam TicketType ticketType, @RequestParam(required = false) String voucher) {

        if (voucher != null) {
            return ticketService.calculateTicketPrice(screeningId, ticketType, voucher);
        }

        return ticketService.calculateTicketPrice(screeningId, ticketType);
    }

    @GetMapping(value = "{id}/expirationTime")
    public LocalDateTime getExpirationTime(@PathVariable Long id) {

        return orderService.getExpirationTime(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody OrderRequest or) {

        List<Long> seatsIds = or.getSeatsIds();
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < seatsIds.size(); i++) {
            seats.add(seatService.find(seatsIds.get(i)));
        }
        List<Long> ticketsIds = or.getTicketsIds();
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketsIds.size(); i++) {
            tickets.add(ticketService.find(seatsIds.get(i)));
        }

        Order order = new Order(or.getTitle(), or.getDescription(), or.getPaymentType(), or.getType(), appUserService.find(or.getAppUserId()), clientService.find(or.getClientId()), screeningService.find(or.getScreeningId()));
        order.setSeats(seats);
        order.setTickets(tickets);
        order.setId(or.getId());

        orderService.save(order);
        LOGGER.info("Order id=" + or.getId() + "updated.");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete(Long id) {

        orderService.delete(id);
        LOGGER.info("Order, id=" + id + "deleted.");
    }
}
