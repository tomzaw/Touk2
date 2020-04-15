package ticketbooking.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private int rowNumber;

    @Positive
    private int number;

    @ManyToOne
    private Room room;

    @OneToMany(mappedBy = "seat")
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToMany(mappedBy = "seats")
    private List<Order> orders = new ArrayList<>();

    public Seat(int rowNumber, int number, Room room) {
        this.rowNumber = rowNumber;
        this.number = number;
        this.room = room;
    }

    public Seat(int rowNumber, int number) {
        this.rowNumber = rowNumber;
        this.number = number;
    }

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Seat{" + "id=" + id + ", rowNumber=" + rowNumber + ", number=" + number + ", room=" + room + ", tickets=" + tickets + ", orders=" + orders + '}';
    }
}
