package ticketbooking.fixture;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ticketbooking.model.Movie;
import ticketbooking.model.Screening;
import ticketbooking.model.Room;
import ticketbooking.model.Seat;
import ticketbooking.model.Client;
import ticketbooking.model.AppUser;
import ticketbooking.model.Order;
import ticketbooking.model.Ticket;
import ticketbooking.repository.MovieRepository;
import ticketbooking.repository.ScreeningRepository;
import ticketbooking.repository.RoomRepository;
import ticketbooking.repository.SeatRepository;
import ticketbooking.repository.ClientRepository;
import ticketbooking.repository.OrderRepository;
import ticketbooking.repository.TicketRepository;
import ticketbooking.repository.AppUserRepository;
import ticketbooking.struct.OrderType;
import ticketbooking.struct.TicketType;

@Component
public class TestDataFixture {

    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final AppUserRepository userRepository;

    public TestDataFixture(MovieRepository movieRepository, ScreeningRepository screeningRepository, RoomRepository roomRepository, SeatRepository seatRepository,
            ClientRepository clientRepository, OrderRepository orderRepository, TicketRepository ticketRepository, AppUserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

        Movie movie1 = new Movie("movie1", "description1", 120);
        Movie movie2 = new Movie("bmovie2", "description2", 120);
        Movie movie3 = new Movie("movie3", "description3", 120);
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);

        Room room1 = new Room("room1");
        Room room2 = new Room("room2");
        Room room3 = new Room("room3");
        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);

        Screening screening1 = new Screening(LocalDateTime.now(), movieRepository.findById(1L).get(), roomRepository.findById(1L).get());
        screeningRepository.save(screening1);
        Screening screening2 = new Screening(LocalDateTime.now().plusHours(4), movieRepository.findById(2L).get(), roomRepository.findById(1L).get());
        screeningRepository.save(screening2);

        Screening screening3 = new Screening(LocalDateTime.now().plusMinutes(60), movieRepository.findById(1L).get(), roomRepository.findById(2L).get());
        screeningRepository.save(screening3);
        Screening screening4 = new Screening(LocalDateTime.now().plusHours(4), movieRepository.findById(2L).get(), roomRepository.findById(2L).get());
        screeningRepository.save(screening4);

        Screening screening5 = new Screening(LocalDateTime.now().plusMinutes(20), movieRepository.findById(1L).get(), roomRepository.findById(3L).get());
        screeningRepository.save(screening5);
        Screening screening6 = new Screening(LocalDateTime.parse("2020-08-01T00:00:00"), movieRepository.findById(2L).get(), roomRepository.findById(3L).get());
        screeningRepository.save(screening6);

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                Seat seat1 = new Seat(i, j, room1);
                Seat seat2 = new Seat(i, j, room2);
                Seat seat3 = new Seat(i, j, room3);
                seatRepository.save(seat1);
                seatRepository.save(seat2);
                seatRepository.save(seat3);
            }
        }

        AppUser user2 = new AppUser("user2", "user2@foo.com", "user2");
        user2 = userRepository.save(user2);

        Client client1 = new Client("Client", "Client", "client@foo.com");
        client1 = clientRepository.save(client1);
//        Order order1 = new Order("order1", "order1", "card", OrderType.RESERVATION, user2, client1, screeningRepository.findById(2L).get());
//        order1.getSeats().add(seatRepository.findById(1L).get());
//        order1 = orderRepository.save(order1);
//
//        Ticket ticket1 = new Ticket(2, TicketType.ADULT, false, BigDecimal.valueOf(25), screeningRepository.findById(2L).get(),
//                seatRepository.findById(1L).get(), order1);
//        ticketRepository.save(ticket1);
    }
}
