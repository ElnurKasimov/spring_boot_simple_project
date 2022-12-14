package SpringBoot.App.sequrity;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final NamedParameterJdbcTemplate jdbcTemplate;


    public String checkCredentials (String email, String password) {
        String passwordFromDb = jdbcTemplate.queryForObject(
                "SELECT password FROM  \"user\"  WHERE email = :email",
                Map.of("email", email),
                String.class
        );

        if ( passwordFromDb == null ) return "unknown User";
        else  return "wrong Password";

    }

}
