package ticketbooking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ticketbooking.model.Screening;
import ticketbooking.repository.ScreeningRepository;

@Transactional
@Service
public class ScreeningService {

    private ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public boolean checkIfAvailableForBooking(Long screeningId) {

        Screening screening = screeningRepository.findById(screeningId).get();
        if (screening.getDate().minusMinutes(15).isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    public List<Screening> findAll() {

        List<Screening> screenings = screeningRepository.findAll();
        return screenings;
    }

    //Returns all screenings for a given date.
    public List<Screening> findAllByDate(LocalDate date) {

        List<Screening> screenings = screeningRepository.findAllByDate(date);
        return screenings;
    }

    //Returns all screenings for a given date sorted by movie title and then by date.
    public List<Screening> findAllByDateSortedByMovieTitleAndDate(LocalDate date) {

        List<Screening> screenings = screeningRepository.findAllByDate(date);

        Comparator<Screening> sortByTitle = (a, b) -> a.getMovie().getTitle().compareToIgnoreCase(b.getMovie().getTitle());
        Comparator<Screening> sortByDate = (a, b) -> a.getDate().compareTo(b.getDate());

        screenings = screenings.stream().sorted(
                sortByTitle.thenComparing(sortByDate))
                .collect(Collectors.toList());

        return screenings;
    }

    public Screening find(Long id) {

        Screening screening = screeningRepository.findById(id).get();
        return screening;
    }

    public List<Screening> findAllByDateBefore(LocalDateTime datetime) {

        List<Screening> screenings = screeningRepository.findAllByDateBefore(datetime);
        return screenings;
    }

    public List<Screening> findAllByDateAfter(LocalDateTime datetime) {

        List<Screening> screenings = screeningRepository.findAllByDateAfter(datetime);
        return screenings;
    }

    public List<Screening> findAllByDateBetween(LocalDateTime datetime1, LocalDateTime datetime2) {

        List<Screening> screenings = screeningRepository.findAllByDateBetween(datetime1, datetime2);
        return screenings;
    }

    public void save(Screening screening) {

        screeningRepository.save(screening);
    }

    public void delete(Long id) {

        screeningRepository.deleteById(id);
    }
}
