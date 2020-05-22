package pl.Gruzdzis.SpringBoot_Gallery_APP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.Gruzdzis.SpringBoot_Gallery_APP.Model.AppUser;
import pl.Gruzdzis.SpringBoot_Gallery_APP.Model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
