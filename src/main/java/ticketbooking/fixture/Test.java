package ticketbooking.fixture;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ticketbooking.model.Movie;
import ticketbooking.model.Screening;
import ticketbooking.model.Room;
import ticketbooking.model.Seat;
import ticketbooking.repository.MovieRepository;
import ticketbooking.repository.ScreeningRepository;
import ticketbooking.repository.RoomRepository;
import ticketbooking.repository.SeatRepository;

public class Test {

    public static void main(String... args) {

        Pattern p = Pattern.compile("(([A-Z]+)|([A-Z]+.*_[A-Z]+))[a-z]*");
        //Pattern p = Pattern.compile("(([A-Z]+.*_[A-Z]+)).*");
        Matcher m = p.matcher("Asb");

        System.out.println(m.matches());

        //List<Screening> screenings = new ArrayList<>();
        //Screening screening1 = new Screening(LocalDateTime.now().plusMinutes(60), movieRepository.findById(1L).get(), roomRepository.findById(1L).get());
        //screenings.add(new Screening());
//        List<Seat> seats = new ArrayList<>();
//        seats.add(new Seat(1, 3));
//        seats.add(new Seat(1, 9));
//        seats.add(new Seat(1, 5));
//        seats.add(new Seat(1, 2));
//        seats.add(new Seat(1, 1));
//        System.out.println(checkIfAvailableForBooking(seats));

    }

    //There cannot be a single place left in a row between two already reserved places.
    public static boolean checkIfAvailableForBooking(List<Seat> seats) {

        seats.sort((a, b) -> a.getNumber() - b.getNumber());

        for (int i = 0; i < seats.size() - 1; i++) {

            if (seats.get(i).getRowNumber() == seats.get(i + 1).getRowNumber()) {
                System.out.println(Math.abs(seats.get(i).getNumber() - seats.get(i + 1).getNumber()));
                if (Math.abs(seats.get(i).getNumber() - seats.get(i + 1).getNumber()) == 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
