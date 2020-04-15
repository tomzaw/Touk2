package ticketbooking.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason="Booking not available. There cannot be a single place left in a row between two already reserved places.")
public class SeatException extends RuntimeException {
}
