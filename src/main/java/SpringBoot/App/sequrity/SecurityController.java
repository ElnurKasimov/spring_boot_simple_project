package SpringBoot.App.sequrity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/security")
@RestController
public class SecurityController {
    private final AuthService authService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public SecurityResponse get() {
        return authService.getSecurityResponse();
    }
}
