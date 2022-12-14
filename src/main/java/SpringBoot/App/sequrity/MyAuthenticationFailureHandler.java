package SpringBoot.App.sequrity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@Service
public class MyAuthenticationFailureHandler
        implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    private AuthService authService;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {

        Map<String, Object> data = new HashMap<>();
        String result = authService.checkCredentials(request.getParameter("username"), request.getParameter("password"));

        switch(result) {
            case "unknown User":
                data.put("unknown User", "Please carry out registration in /registration");
                break;
            case "wrong Password" :
                data.put("wrong Password", "Please enter correct one");
                break;
            default:
                data.put("exception", exception.getMessage());

        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.getOutputStream()
                .println(objectMapper.writeValueAsString(data));
    }
}
