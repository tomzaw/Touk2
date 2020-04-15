package ticketbooking.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import ticketbooking.struct.TicketType;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TicketType type;

    private boolean isPromotion;

    @NotNull
    @Positive
    private BigDecimal price;

    @ManyToOne
    private Screening screening;

    @ManyToOne
    private Seat seat;

    @ManyToOne
    private Order order;

    public Ticket(TicketType type, boolean isPromotion, BigDecimal price, Screening screening, Seat seat, Order order) {
        this.type = type;
        this.isPromotion = isPromotion;
        this.price = price;
        this.screening = screening;
        this.seat = seat;
        this.order = order;
    }

    public Ticket(TicketType type, boolean isPromotion, BigDecimal price) {
        this.type = type;
        this.isPromotion = isPromotion;
        this.price = price;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public boolean isIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(boolean isPromotion) {
        this.isPromotion = isPromotion;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrders(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", type=" + type + ", isPromotion=" + isPromotion + ", price=" + price + ", screening=" + screening + ", seat=" + seat + ", order=" + order + '}';
    }
}
