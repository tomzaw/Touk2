package ticketbooking.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason="Order for seats from different room than screening is not be possible.")
public class OrderException extends RuntimeException {
}
