package SpringBoot.App.role;

import SpringBoot.App.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("FROM Role r WHERE r.name LIKE :name")
    Role getByName(@Param("name") String name);

}
