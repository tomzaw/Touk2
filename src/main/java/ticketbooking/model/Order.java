package ticketbooking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import ticketbooking.struct.OrderType;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String paymentType;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderType type;

    @NotNull
    private LocalDateTime orderDate;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Screening screening;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_seat",
            joinColumns = {
                @JoinColumn(name = "order_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "seat_id")}
    )
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<Ticket> tickets = new ArrayList<>();

    public Order(String title, String description, String paymentType, OrderType type, AppUser appUser, Client client, Screening screening) {
        this.title = title;
        this.description = description;
        this.paymentType = paymentType;
        this.type = type;
        this.orderDate = LocalDateTime.now();
        this.appUser = appUser;
        this.client = client;
        this.screening = screening;
    }

    public Order() {
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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", title=" + title + ", description=" + description + ", paymentType=" + paymentType + ", type=" + type + ", orderDate=" + orderDate + ", appUser=" + appUser + ", client=" + client + ", screening=" + screening + ", seats=" + seats + ", tickets=" + tickets + '}';
    }
}
