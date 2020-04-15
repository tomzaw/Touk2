package ticketbooking.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class SeatRequest {

    @NotNull
    private Long id;

    @Positive
    private int rowNumber;

    @Positive
    private int number;

    @NotNull
    private Long roomId;

    public SeatRequest(Long id, int rowNumber, int number, Long roomId) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.number = number;
        this.roomId = roomId;
    }

    public SeatRequest() {
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
