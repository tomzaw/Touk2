package ticketbooking.web.controller;

import java.util.ArrayList;
import java.util.List;
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
import ticketbooking.model.Seat;
import ticketbooking.service.SeatService;
import ticketbooking.service.RoomService;
import ticketbooking.web.CreateSeatRequest;
import ticketbooking.web.SeatRequest;
import ticketbooking.web.SeatResponse;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeatController.class);

    private SeatService seatService;

    private RoomService roomService;

    public SeatController(SeatService seatService, RoomService roomService) {
        this.seatService = seatService;
        this.roomService = roomService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody CreateSeatRequest csr) {

        Seat seat = new Seat(csr.getRowNumber(), csr.getNumber(), roomService.find(csr.getRoomId()));
        seatService.save(seat);
        LOGGER.info("Seat created");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SeatResponse read(@PathVariable Long id) {

        Seat seat = seatService.find(id);
        return new SeatResponse(seat.getId(), seat.getRowNumber(), seat.getNumber(), seat.getRoom().getId());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SeatResponse> readAll() {

        List<Seat> seats = seatService.findAll();
        List<SeatResponse> seatsResponses = new ArrayList<>();
        for (Seat seat : seats) {
            seatsResponses.add(new SeatResponse(seat.getId(), seat.getRowNumber(), seat.getNumber(), seat.getRoom().getId()));
        }
        return seatsResponses;
    }

    @GetMapping(params = "roomId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SeatResponse> readAllForRoom(@RequestParam Long roomId) {

        List<Seat> seats = seatService.findAllByRoomId(roomId);
        List<SeatResponse> seatsResponses = new ArrayList<>();
        for (Seat seat : seats) {
            seatsResponses.add(new SeatResponse(seat.getId(), seat.getRowNumber(), seat.getNumber(), seat.getRoom().getId()));
        }
        return seatsResponses;
    }

    @GetMapping(params = "screeningId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SeatResponse> readAllAvailableForScreening(@RequestParam Long screeningId) {

        List<Seat> seats = seatService.findAllAvailableForScreening(screeningId);
        List<SeatResponse> seatsResponses = new ArrayList<>();
        for (Seat seat : seats) {
            seatsResponses.add(new SeatResponse(seat.getId(), seat.getRowNumber(), seat.getNumber(), seat.getRoom().getId()));
        }
        return seatsResponses;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody SeatRequest sr) {

        Seat seat = new Seat(sr.getRowNumber(), sr.getNumber(), roomService.find(sr.getRoomId()));
        seat.setId(sr.getId());
        seatService.save(seat);
        LOGGER.info("Seat id=" + sr.getId() + "updated");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete(Long id) {

        seatService.delete(id);
        LOGGER.info("Seat id=" + id + "deleted");
    }
}
