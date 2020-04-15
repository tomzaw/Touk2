package ticketbooking.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ticketbooking.model.Room;
import ticketbooking.repository.RoomRepository;

@Transactional
@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {

        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }

    public Room find(Long id) {

        Room room = roomRepository.findById(id).get();
        return room;
    }

    public void save(Room room) {

        roomRepository.save(room);
    }

    public void delete(Long id) {

        roomRepository.deleteById(id);
    }
}
