package ticketbooking.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ticketbooking.model.Screening;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("Select s from Screening s where s.date like concat(:date,'%')")
    List<Screening> findAllByDate(@Param("date") LocalDate date);

    List<Screening> findAllByDateBefore(LocalDateTime datetime);

    List<Screening> findAllByDateAfter(LocalDateTime datetime);

    List<Screening> findAllByDateBetween(LocalDateTime datetime1, LocalDateTime datetime2);
}
