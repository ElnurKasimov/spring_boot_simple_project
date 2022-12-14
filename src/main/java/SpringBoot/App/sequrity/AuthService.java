package SpringBoot.App.sequrity;

import SpringBoot.App.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public void save(String lastName, String firstName, String email, String password) {
//        User user = new User();
//        user.setId(UUID.randomUUID());
//        user.setLastName(lastName));
//        user.setFirstName(firstName);
//        user.setEmail(email);
//        user.setPassword(encoder.encode(password));
//        return user;

        UUID newUserId =  UUID.randomUUID();

        jdbcTemplate.update(
                "INSERT INTO \"user\"  (id, last_name, first_name, email, password)  VALUES (:id, :lastName, :firstName, :email, :password)",
                Map.of("id", newUserId,
                        "lastName", lastName,
                        "firstName", firstName,
                        "email", email,
                        "password", encoder.encode("password")
                )



        );
    }


}
