package SpringBoot.App.product;

import SpringBoot.App.manufacture.ManufactureService;
import SpringBoot.App.product.dto.ProductDto;
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
@RequestMapping("/product")
public class ProductController {
    private  final ProductService productService;
    private final ManufactureService manufactureService;

    @GetMapping("/all")
    public ModelAndView getListOfAllProducts() {
        ModelAndView result = new ModelAndView("product/all");
        result.addObject("products", productService.listAll());
        return  result;
    }

    @GetMapping("/id")
    public String getProductById() {
        return "/product/getById";
    }

    @PostMapping("/id")
    public ModelAndView getProductById(@RequestParam(name = "id") String id) {
        ModelAndView result = new ModelAndView("product/getById");
        result.addObject("product", productService.getById(UUID.fromString(id)));
        return result;
    }

    @GetMapping("/name")
    public String getProductByName() {
        return "/product/getByName";
    }

    @PostMapping("/name")
    public ModelAndView getProductByName(@RequestParam(name = "name") String name) {
        ModelAndView result = new ModelAndView("product/getByName");
        result.addObject("product", productService.getByName(name));
        return result;
    }

    @GetMapping("/add")
    public ModelAndView getAddProduct() {
        ModelAndView result = new ModelAndView("product/add");
        result.addObject("manufactures", manufactureService.listAll());
        return  result;
    }
    @PostMapping("/add")
    public String postAddProduct(
            @RequestParam ("name") String name,
            @RequestParam ("price") long price,
            @RequestParam ("manufactureName") String manufactureName) {
        ProductDto productDto = new ProductDto(name, price, manufactureService.getByName(manufactureName));
        productService.save(productDto);
        return "redirect:/product/all";
    }

    @GetMapping("/update")
    public ModelAndView getUpdateProduct() {
        ModelAndView result = new ModelAndView("product/update");
        result.addObject("manufactures", manufactureService.listAll());
        return  result;
    }
    @PostMapping("/update")
    public String postUpdateProduct(
            @RequestParam ("name") String name,
            @RequestParam ("price") long price,
            @RequestParam ("manufactureName") String manufactureName) {
        ProductDto forUpdateDto = productService.getByName(name);
        if(forUpdateDto != null) {
            forUpdateDto.setPrice(price);
            forUpdateDto.setManufacture(manufactureService.getByName(manufactureName));
            productService.save(forUpdateDto);
        return "redirect:/product/all";}
        else {return "redirect:/error";}
    }

    @GetMapping("/delete")
    public String getDeleteProduct() {return "/product/delete";
    }
    @PostMapping("/delete")
    public String postDeleteById(@RequestParam ("id") String id) {
        if(productService.getById(UUID.fromString(id)) != null) {
            productService.deleteById(UUID.fromString(id));
            return "redirect:/product/all";}
        else {return "redirect:/error";}
    }
    @GetMapping("/error")
    public String getErrorPage () {return "redirect:/error";}

}
