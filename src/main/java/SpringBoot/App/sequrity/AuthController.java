package SpringBoot.App.sequrity;

import SpringBoot.App.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@RequiredArgsConstructor
@Controller
    public class AuthController {
    private  final AuthService authService;

        @GetMapping("/login")
        public String login() {
            return "/security/login";
        }

    @GetMapping("/homepage")
    public String homepage() {
        return "/homepage";
    }

//    @GetMapping("/auth")
//    public ModelAndView auth() {
//            if(authService.isRegistered()) { return new ModelAndView("security/login-error");}
//            else { return new ModelAndView("security/registration");}
//    }
//
//    @GetMapping("/login-error")
//    public String loginError() {
//        return "/security/login-error";
//    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "/security/registration";
    }

    @PostMapping("/registration")
    public String postRegistration(
            @RequestParam("lastName") String lastName,
            @RequestParam ("firstName") String firstName,
            @RequestParam ("email") String email,
            @RequestParam ("password") String password) {
        authService.save(lastName, firstName, email, password);
        return "redirect:/login";
    }

}
