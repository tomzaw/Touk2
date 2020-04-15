package ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketbooking.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
