package SpringBoot.App.gui;

import SpringBoot.App.manufacture.ManufactureService;
import SpringBoot.App.manufacture.dto.ManufactureDto;
import SpringBoot.App.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequiredArgsConstructor
@Controller

public class GUIController {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

}
