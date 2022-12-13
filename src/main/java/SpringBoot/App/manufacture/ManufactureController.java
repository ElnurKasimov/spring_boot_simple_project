package SpringBoot.App.manufacture;

import SpringBoot.App.manufacture.dto.ManufactureDto;
import SpringBoot.App.product.InMemoryProductService;
import SpringBoot.App.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/manufacture")
public class ManufactureController {
    private final ManufactureService manufactureService;
    private final ProductService productService;

    @GetMapping("/all")
    public ModelAndView getSetOfProducts() {
        ModelAndView result = new ModelAndView("manufacture/all");
        result.addObject("manufactures", manufactureService.listAll());
        return  result;
    }

    @GetMapping("/id")
    public String getManufactureById() {
        return "/manufacture/getById";
    }

    @PostMapping("/id")
    public ModelAndView getManufactureById(@RequestParam(name = "id") String id) {
        ModelAndView result = new ModelAndView("manufacture/getById");
        result.addObject("manufacture", manufactureService.getById(UUID.fromString(id)));
        result.addObject("manufactureProducts", productService.getManufactureProductsById(UUID.fromString(id)));
        return result;
    }

    @GetMapping("/name")
    public String getManufactureByName() {
        return "/manufacture/getByName";
    }

    @PostMapping("/name")
    public ModelAndView getManufactureByName(@RequestParam(name = "name") String name) {
        ModelAndView result = new ModelAndView("manufacture/getByName");
        result.addObject("manufacture", manufactureService.getByName(name));
        result.addObject("manufactureProducts", productService.getManufactureProductsByName(name));
        return result;
    }

    @GetMapping("/add")
    public String getAddManufacture() {
        //ModelAndView result = new ModelAndView("manufacture/add");
        return "/manufacture/add";
    }
    @PostMapping("/add")
    public String postAddManufacture(@RequestParam ("name") String name) {
        ManufactureDto manufactureDto = new ManufactureDto(name);
        manufactureService.save(manufactureDto);
        return "redirect:/manufacture/all";
    }

    @GetMapping("/update")
    public String getUpdateManufacture() {
        //odelAndView result = new ModelAndView("manufacture/add");
        return "/manufacture/update";
    }
    @PostMapping("/update")
    public String postUpdateManufacture(
            @RequestParam ("id") String id,
            @RequestParam ("name") String name
            ) {
        ManufactureDto manufactureDto = new ManufactureDto(UUID.fromString(id),name);
        manufactureService.save(manufactureDto);
        return "redirect:/manufacture/all";
    }

    @GetMapping("/error")
    public String getErrorPage () {
        return "redirect:/error";
    }

    @GetMapping("/delete")
    public String getDeleteManufacture() {
        return "/manufacture/delete";
    }

    @PostMapping("/delete")
    public String postDeleteById(@RequestParam ("id") String id) {
        if(manufactureService.getById(UUID.fromString(id)) != null) {
            productService.deleteManufactureProducts(UUID.fromString(id));
            manufactureService.deleteById(UUID.fromString(id));
            return "redirect:/manufacture/all";}
        else {return "redirect:/error";}
    }

}
