package ticketbooking.web;

import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;

public class CreateSeatRequest {

    @Positive
    private int rowNumber;

    @Positive
    private int number;

    @NotNull
    private Long roomId;

    public CreateSeatRequest(int rowNumber, int number, Long roomId) {
        this.rowNumber = rowNumber;
        this.number = number;
        this.roomId = roomId;
    }

    public CreateSeatRequest() {
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
