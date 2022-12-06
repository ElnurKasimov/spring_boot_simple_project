package SpringBoot.App.manufacture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, UUID> {
    @Query ("FROM Manufacture m WHERE m.name LIKE :name")
    Manufacture getByName(@Param("name") String name);
}
