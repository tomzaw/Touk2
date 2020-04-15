package ticketbooking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketbooking.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    void deleteByUsername(String username);

    Optional<AppUser> findByUsername(String username);
}
