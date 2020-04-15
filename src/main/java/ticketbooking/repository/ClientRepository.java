package ticketbooking.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketbooking.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByName(String username);
    
    List<Client> findAllByName(String username);
    
    Optional<Client> findBySurname(String username);
    
    List<Client> findAllBySurname(String username);
}
