package SpringBoot.App.user;

import SpringBoot.App.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("FROM User u WHERE u.lastName LIKE :lastName AND u.firstName LIKE :firstName")
    User getByName(@Param("lastName") String lastName,
                   @Param("firstName") String firstName);


}
