package SpringBoot.App.role;

import SpringBoot.App.user.UserService;
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
@RequestMapping("/role")
public class RoleController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/all")
    public ModelAndView getSetOfRoles() {
        ModelAndView result = new ModelAndView("role/all");
        result.addObject("roles", roleService.listAll());
        return  result;
    }

    @GetMapping("/id")
    public String getRoleById() {return "/role/getById";}

    @PostMapping("/id")
    public ModelAndView postGetRoleById(@RequestParam(name = "id") String id) {
        ModelAndView result = new ModelAndView("role/getById");
        result.addObject("role", roleService.getById(UUID.fromString(id)));
        return result;
    }


    @GetMapping("/name")
    public String getRoleByName() {return "/role/getByName";}

    @PostMapping("/name")
    public ModelAndView postGetRoleByName(@RequestParam(name = "name") String name) {
        ModelAndView result = new ModelAndView("role/getByName");
        result.addObject("role", roleService.getByName(name));
        return result;
    }

    @GetMapping("/add")
    public String getAddRole() {return "/role/add";}

    @PostMapping("/add")
    public String postAddRole(@RequestParam(name = "name") String name )  {
        Role role = new Role(name);
        roleService.save(role);
        return "redirect:/role/all";
    }

    @GetMapping("/error")
    public String getErrorPage () {
        return "/error";
    }

    @GetMapping("/delete")
    public String getDeleteRoleById() {
        return "/role/delete";
    }

    @PostMapping("/delete")
    public String postDeleteRoleById(@RequestParam ("id") String id) {
        if(roleService.getById(UUID.fromString(id)) != null) {
            roleService.deleteById(UUID.fromString(id));
            return "redirect:/role/all";}
        else {return "redirect:/error";}
    }

}
