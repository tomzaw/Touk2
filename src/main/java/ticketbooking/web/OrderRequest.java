package ticketbooking.web;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import ticketbooking.struct.OrderType;

public class OrderRequest {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    private String description;

    @NotBlank
    private String paymentType;

    @NotNull
    private OrderType type;

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private Long appUserId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long screeningId;

    @NotNull
    private List<Long> seatsIds;

    @NotNull
    private List<Long> ticketsIds;

    public OrderRequest(Long id, String title, String description, String paymentType, OrderType type, LocalDateTime orderDate, Long appUserId, Long clientId, Long screeningId, List<Long> seatsIds, List<Long> ticketsIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.paymentType = paymentType;
        this.type = type;
        this.orderDate = orderDate;
        this.appUserId = appUserId;
        this.clientId = clientId;
        this.screeningId = screeningId;
        this.seatsIds = seatsIds;
        this.ticketsIds = ticketsIds;
    }

    public OrderRequest(Long id, String title, String description, String paymentType, OrderType type, LocalDateTime orderDate, Long appUserId, Long clientId, Long screeningId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.paymentType = paymentType;
        this.type = type;
        this.orderDate = orderDate;
        this.appUserId = appUserId;
        this.clientId = clientId;
        this.screeningId = screeningId;
    }

    public OrderRequest() {
        this.orderDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Long getAppUserId() {
        return appUserId;
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

    public List<Long> getSeatsIds() {
        return seatsIds;
    }

    public void setSeatsIds(List<Long> seatsIds) {
        this.seatsIds = seatsIds;
    }

    public List<Long> getTicketsIds() {
        return ticketsIds;
    }

    public void setTicketsIds(List<Long> ticketsIds) {
        this.ticketsIds = ticketsIds;
    }
}
