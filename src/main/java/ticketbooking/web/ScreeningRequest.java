package ticketbooking.web;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class ScreeningRequest {

    @Id
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Long movieId;

    @NotNull
    private Long roomId;

    @NotNull
    private List<Long> orderIds;

    public ScreeningRequest(Long id, LocalDateTime date, Long movieId, Long roomId, List<Long> orderIds) {
        this.id = id;
        this.date = date;
        this.movieId = movieId;
        this.roomId = roomId;
        this.orderIds = orderIds;
    }

    public ScreeningRequest(Long id, LocalDateTime date, Long movie, Long roomId) {
        this.id = id;
        this.date = date;
        this.movieId = movieId;
        this.roomId = roomId;
    }

    public ScreeningRequest() {
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

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

}
