package SpringBoot.App.sequrity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@RequiredArgsConstructor
@Controller
    public class AuthController {
    private  final AuthService authService;

        @GetMapping("/login")
        public String login() {
            return "/login";
        }

    @GetMapping("/homepage")
    public String homepage() {
        return "/homepage";
    }

    @GetMapping("/auth")
    public String auth() {
        return authService.isRegistered() ? "/login/error" : "/registration";
    }

    @GetMapping("/login/error")
    public String loginError() {
        return "/login/error";
    }


}
