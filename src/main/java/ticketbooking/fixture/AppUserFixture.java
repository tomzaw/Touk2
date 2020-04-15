package ticketbooking.fixture;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ticketbooking.model.AppUser;
import ticketbooking.repository.AppUserRepository;

@Component
public class AppUserFixture {

    private final AppUserRepository appUserRepository;

    public AppUserFixture(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostConstruct
    public void init() {

        AppUser root = new AppUser("root", "name1@foo.com", "root");
        AppUser user1 = new AppUser("user1", "user1@foo.com", "user1");

        appUserRepository.save(root);
        appUserRepository.save(user1);
    }
}
