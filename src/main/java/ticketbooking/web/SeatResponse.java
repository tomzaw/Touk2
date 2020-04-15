package ticketbooking.web;

public class SeatResponse {

    private Long id;

    private int rowNumber;

    private int number;

    private Long roomId;

    public SeatResponse(Long id, int rowNumber, int number, Long roomId) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.number = number;
        this.roomId = roomId;
    }

    public SeatResponse() {
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
