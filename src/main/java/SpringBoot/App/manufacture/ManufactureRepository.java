package SpringBoot.App.manufacture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, UUID> {

}
