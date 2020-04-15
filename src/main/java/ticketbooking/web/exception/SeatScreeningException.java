package ticketbooking.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason="The same seat should not be reserved more than one time for the same screening.")
public class SeatScreeningException extends RuntimeException {
}
