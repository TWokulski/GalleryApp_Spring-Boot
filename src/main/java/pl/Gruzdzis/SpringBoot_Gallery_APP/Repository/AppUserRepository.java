package pl.Gruzdzis.SpringBoot_Gallery_APP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.Gruzdzis.SpringBoot_Gallery_APP.Model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
