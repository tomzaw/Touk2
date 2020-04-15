package ticketbooking.web;

import javax.validation.constraints.Positive;
import ticketbooking.struct.TicketType;
import javax.validation.constraints.NotNull;

public class CreateTicketRequest {

    @NotNull
    private TicketType type;

    @NotNull
    @Positive
    private Long seatId;

    public CreateTicketRequest(TicketType type) {
        this.type = type;
    }

    public CreateTicketRequest() {
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }
    
    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
}
