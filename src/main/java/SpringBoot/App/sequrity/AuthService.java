package SpringBoot.App.sequrity;

import SpringBoot.App.role.Role;
import SpringBoot.App.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private  final PasswordEncoder encoder;

    public void save(String lastName, String firstName, String email, String password) {
        UUID newUserId =  UUID.randomUUID();
        UUID roleId = findByName("ROLE_USER").get(0).getId();
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

}
