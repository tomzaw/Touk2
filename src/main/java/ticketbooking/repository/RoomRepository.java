package ticketbooking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketbooking.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    void deleteByName(String name);

    Optional<Room> findByName(String name);
}
