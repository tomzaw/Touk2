package ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketbooking.model.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}
