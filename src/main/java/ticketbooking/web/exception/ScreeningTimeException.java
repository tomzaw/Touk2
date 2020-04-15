package ticketbooking.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Booking not available 15 minutes before screening.")
public class ScreeningTimeException extends RuntimeException {
}
