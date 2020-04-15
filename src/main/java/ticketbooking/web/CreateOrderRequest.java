package ticketbooking.web;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import ticketbooking.struct.OrderType;

public class CreateOrderRequest {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String paymentType;

    @NotNull
    private OrderType type;

    @NotNull
    private Long appUserId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long screeningId;

    //@NotNull
    //private List<Long> seatsIds;

    @NotNull
    private List<CreateTicketRequest> tickets;

    public CreateOrderRequest(String title, String description, String paymentType, OrderType type, Long appUserId, Long clientId, Long screeningId, List<CreateTicketRequest> tickets) {
        this.title = title;
        this.description = description;
        this.paymentType = paymentType;
        this.type = type;
        this.appUserId = appUserId;
        this.clientId = clientId;
        this.screeningId = screeningId;
        this.tickets = tickets;
    }

    public CreateOrderRequest(String title, String description, String paymentType, OrderType type, Long appUserId, Long clientId, Long screeningId) {
        this.title = title;
        this.description = description;
        this.paymentType = paymentType;
        this.type = type;
        this.appUserId = appUserId;
        this.clientId = clientId;
        this.screeningId = screeningId;
    }

    public CreateOrderRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

//    public List<Long> getSeatsIds() {
//        return seatsIds;
//    }
//
//    public void setSeatsIds(List<Long> seatsIds) {
//        this.seatsIds = seatsIds;
//    }

    public List<CreateTicketRequest> getTickets() {
        return tickets;
    }

    public void setTickets(List<CreateTicketRequest> tickets) {
        this.tickets = tickets;
    }
}
