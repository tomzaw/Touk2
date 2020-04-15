package ticketbooking.service;

import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ticketbooking.model.Screening;
import ticketbooking.model.Seat;
import ticketbooking.repository.SeatRepository;
import ticketbooking.repository.ScreeningRepository;

@Transactional
@Service
public class SeatService {

    private SeatRepository seatRepository;

    private ScreeningRepository screeningRepository;

    public SeatService(SeatRepository seatRepository, ScreeningRepository screeningRepository) {
        this.seatRepository = seatRepository;
        this.screeningRepository = screeningRepository;
    }

    //There cannot be a single place left in a row between two already reserved places.
    public boolean checkIfAvailableForBooking(List<Seat> seats) {

        seats.sort((a, b) -> a.getNumber() - b.getNumber());
        for (int i = 0; i < seats.size() - 1; i++) {

            if (seats.get(i).getRowNumber() == seats.get(i + 1).getRowNumber()) {

                if (Math.abs(seats.get(i).getNumber() - seats.get(i + 1).getNumber()) == 2) {
                    return false;
                }
            }
        }
        return true;
    }

    //The same seat should not be reserved more than one time for the same screening.
    public boolean checkIfAvailableForBooking(Long screeningId, List<Long> seatsIds) {

        List<Long> reservedSeatsIds = seatRepository.findAllReservedIdsForScreening(screeningId);
        for (int i = 0; i < seatsIds.size(); i++) {
            for (int j = 0; j < reservedSeatsIds.size(); j++) {
                if (Objects.equals(seatsIds.get(i), reservedSeatsIds.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Seat> findAll() {

        List<Seat> seats = seatRepository.findAll();
        return seats;
    }

    public List<Seat> findAllByRoomId(Long id) {

        List<Seat> seats = seatRepository.findAllByRoomId(id);
        return seats;
    }

    public List<Seat> findAllAvailableForScreening(Long screeningId) {

        Screening screening = screeningRepository.findById(screeningId).get();
        List<Seat> seats = seatRepository.findAllByRoomId(screening.getRoom().getId());
        List<Long> seatsReservedIds = seatRepository.findAllReservedIdsForScreening(screeningId);
        for (int i = 0; i < seatsReservedIds.size(); i++) {
            for (int j = 0; j < seats.size(); j++) {
                if (Objects.equals(seatsReservedIds.get(i), seats.get(j).getId())) {
                    seats.remove(j);
                }
            }
        }
        return seats;
    }

    public Seat find(Long id) {

        Seat seat = seatRepository.findById(id).get();
        return seat;
    }

    public void save(Seat seat) {

        seatRepository.save(seat);
    }

    public void delete(Long id) {

        seatRepository.deleteById(id);
    }
}
