package ticketbooking.fixture;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ticketbooking.model.Voucher;
import ticketbooking.repository.VoucherRepository;

@Component
public class VoucherFixture {

    private final VoucherRepository voucherRepository;

    public VoucherFixture(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @PostConstruct
    public void init() {

        Voucher voucher1 = new Voucher("code1");
        Voucher voucher2 = new Voucher("code2");

        voucherRepository.save(voucher1);
        voucherRepository.save(voucher2);
    }
}
