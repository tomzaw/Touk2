package ticketbooking.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ticketbooking.model.Seat;
import ticketbooking.repository.SeatRepository;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatService seatService;

    @Test
    public void shouldBook() {
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(1, 1));
        seats.add(new Seat(1, 5));
        seats.add(new Seat(1, 6));

        assertEquals(true, seatService.checkIfAvailableForBooking(seats));
    }

    @Test
    public void shouldNotBook() {
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(1, 1));
        seats.add(new Seat(1, 3));
        seats.add(new Seat(1, 4));

        assertEquals(false, seatService.checkIfAvailableForBooking(seats));
    }
}
