package SpringBoot.App.user;

import SpringBoot.App.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("FROM User u WHERE u.lastName LIKE :lastName AND u.firstName LIKE :firstName")
    User getByName(@Param("lastName") String lastName, @Param("firstName") String firstName);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value=
    "DELETE FROM role_user WHERE  role_id = ?1")
    void deleteRoleFromUsers(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value=
            "INSERT INTO role_user (role_id, user_id)  VALUES (:role_id, :user_id)")
    void saveRoleRelations(@Param("role_id") UUID role_id, @Param("user_id") UUID user_id);


}
