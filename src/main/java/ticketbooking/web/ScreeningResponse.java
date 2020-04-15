package ticketbooking.web;

import java.time.LocalDateTime;
import java.util.List;

public class ScreeningResponse {

    private Long id;

    private LocalDateTime date;

    private Long movieId;

    private Long roomId;

    private List<Long> ordersIds;

    public ScreeningResponse(Long id, LocalDateTime date, Long movieId, Long roomId, List<Long> ordersIds) {
        this.id = id;
        this.date = date;
        this.movieId = movieId;
        this.roomId = roomId;
        this.ordersIds = ordersIds;
    }

    public ScreeningResponse(Long id, LocalDateTime date, Long movieId, Long roomId) {
        this.id = id;
        this.date = date;
        this.movieId = movieId;
        this.roomId = roomId;
    }

    public ScreeningResponse() {
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
