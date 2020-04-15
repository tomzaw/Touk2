package ticketbooking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ticketbooking.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByRoomId(Long id);

    @Query(value = "SELECT seat.id FROM SEAT left join order_seat on seat.id=seat_id left join orders on order_seat.order_id=orders.id "
            + "where screening_id=:screeningId", nativeQuery = true)
    List<Long> findAllReservedIdsForScreening(@Param("screeningId") Long screeningId);
}
