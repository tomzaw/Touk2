package ticketbooking.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ticketbooking.model.Ticket;
import ticketbooking.model.Voucher;
import ticketbooking.model.Screening;
import ticketbooking.repository.TicketRepository;
import ticketbooking.repository.ScreeningRepository;
import ticketbooking.repository.VoucherRepository;
import ticketbooking.struct.TicketType;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@Transactional
@Service
public class TicketService {

    private TicketRepository ticketRepository;

    private ScreeningRepository screeningRepository;

    private VoucherRepository voucherRepository;

    public TicketService(TicketRepository ticketRepository, ScreeningRepository screeningRepository, VoucherRepository voucherRepository) {
        this.ticketRepository = ticketRepository;
        this.screeningRepository = screeningRepository;
        this.voucherRepository = voucherRepository;
    }

    public BigDecimal calculateTicketPrice(Long screeningId, TicketType ticketType) {

        BigDecimal price = BigDecimal.ZERO;
        switch (ticketType) {
            case ADULT:
                price = BigDecimal.valueOf(25);
                break;
            case STUDENT:
                price = BigDecimal.valueOf(18);
                break;
            case CHILD:
                price = BigDecimal.valueOf(12.50);
                break;
        }

        Screening screening = screeningRepository.findById(screeningId).get();
        LocalDateTime datetime = screening.getDate();
        if ((datetime.getDayOfWeek() == FRIDAY && datetime.getHour() > 14) || (datetime.getDayOfWeek() == SATURDAY) || (datetime.getDayOfWeek() == SUNDAY && datetime.getHour() < 11)) {
            return price.add(BigDecimal.valueOf(4));
        } else {
            return price;
        }
    }

    public BigDecimal calculateTicketPrice(Long screeningId, TicketType ticketType, String voucherCode) {

        boolean promotion = checkVoucherCode(voucherCode);

        BigDecimal price = BigDecimal.ZERO;
        switch (ticketType) {
            case ADULT:
                price = BigDecimal.valueOf(25);
                break;
            case STUDENT:
                price = BigDecimal.valueOf(18);
                break;
            case CHILD:
                price = BigDecimal.valueOf(12.50);
                break;
        }

        Screening screening = screeningRepository.findById(screeningId).get();
        LocalDateTime datetime = screening.getDate();

        if (promotion) {

            if ((datetime.getDayOfWeek() == FRIDAY && datetime.getHour() > 14) || (datetime.getDayOfWeek() == SATURDAY) || (datetime.getDayOfWeek() == SUNDAY && datetime.getHour() < 11)) {
                return price.add(BigDecimal.valueOf(4)).divide(BigDecimal.valueOf(2));
            } else {
                return price.divide(BigDecimal.valueOf(2));
            }

        } else if ((datetime.getDayOfWeek() == FRIDAY && datetime.getHour() > 14) || (datetime.getDayOfWeek() == SATURDAY) || (datetime.getDayOfWeek() == SUNDAY && datetime.getHour() < 11)) {
            return price.add(BigDecimal.valueOf(4));
        } else {
            return price;
        }
    }

    public boolean checkVoucherCode(String voucherCode) {

        List<Voucher> vouchers = voucherRepository.findAll();

        boolean promotion = false;
        for (Voucher v : vouchers) {
            if (v.getCode().equals(voucherCode)) {
                promotion = true;
            }
        }
        return promotion;
    }

    public List<Ticket> findAll() {

        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    public Ticket find(Long id) {

        Ticket ticket = ticketRepository.findById(id).get();
        return ticket;
    }

    public Ticket save(Ticket ticket) {

        return ticketRepository.save(ticket);
    }

    public List<Ticket> saveAll(List<Ticket> tickets) {

        return ticketRepository.saveAll(tickets);
    }

    public void delete(Long id) {

        ticketRepository.deleteById(id);
    }
}
