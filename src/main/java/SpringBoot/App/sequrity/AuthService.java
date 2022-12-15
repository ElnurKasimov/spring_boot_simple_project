package SpringBoot.App.sequrity;

import SpringBoot.App.role.Role;
import SpringBoot.App.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private  final PasswordEncoder encoder;

    public  boolean isRegistered () {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<User> users = jdbcTemplate.query(
                "SELECT *  FROM \"user\"  WHERE email = :email",
                Map.of("email", username),
                new UserRowMapper()
        );
        return !users.isEmpty();
    }

    public void save(String lastName, String firstName, String email, String password) {
        UUID newUserId =  UUID.randomUUID();
        UUID roleId = findByName("USER").get(0).getId();

        jdbcTemplate.update(
                "INSERT INTO \"user\"  (id, last_name, first_name, email, password)  VALUES (:id, :lastName, :firstName, :email, :password)",
                Map.of("id", newUserId,
                        "lastName", lastName,
                        "firstName", firstName,
                        "email", email,
                        "password", encoder.encode(password)
                )
        );

        jdbcTemplate.update(
                "INSERT INTO role_user (role_id, user_id)  VALUES (:role_id, :user_id)",
                Map.of("role_id", roleId, "user_id", newUserId)
        );
    }

    public List<Role> findByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM role WHERE name = :name",
                Map.of("name", name),
                new RoleRowMapper());
    }

    private static class RoleRowMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("name");
            UUID id = UUID.fromString(rs.getString("id"));
            Role role = new Role();
            role.setName(name);
            role.setId(id);
            return role;
        }
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(UUID.fromString(rs.getString("id")));
            user.setLastName(rs.getString("last_name"));
            user.setFirstName(rs.getString("first_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

}
