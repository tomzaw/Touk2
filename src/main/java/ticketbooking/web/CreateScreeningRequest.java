package ticketbooking.web;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class CreateScreeningRequest {

    @Id
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Long movieId;

    @NotNull
    private Long roomId;

    @NotNull
    private List<Long> ordersIds;

    public CreateScreeningRequest(LocalDateTime date, Long movieId, Long roomId, List<Long> ordersIds) {
        this.date = date;
        this.movieId = movieId;
        this.roomId = roomId;
        this.ordersIds = ordersIds;
    }
    
    public CreateScreeningRequest(LocalDateTime date, Long movieId, Long roomId) {
        this.date = date;
        this.movieId = movieId;
        this.roomId = roomId;
    }

    public CreateScreeningRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public List<Long> getOrdersIds() {
        return ordersIds;
    }

    public void setOrdersIds(List<Long> ordersIds) {
        this.ordersIds = ordersIds;
    }
}
