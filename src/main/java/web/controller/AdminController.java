package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.HashSet;

@Controller
@RequestMapping("/admin")
@ComponentScan(value = "web")
public class AdminController {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping
    public String printCars(ModelMap model) {
        model.addAttribute("users", userServiceImpl.listUsers());
        return "admin";
    }

    @PostMapping("/newuser")
    public String createUser(@ModelAttribute User user,
                             @RequestParam(required = false, name = "userparam") String userParam,
                             @RequestParam(required = false, name = "adminparam") String adminParam) {
        Role roleUser = null;
        Role roleAdmin = null;

        if (adminParam != null) {
            roleAdmin = new Role("ROLE_admin");
            roleAdmin.setClient(user);
            roleUser = new Role("ROLE_user");
            roleUser.setClient(user);
        }

        if (userParam != null && roleAdmin == null) {
            roleUser = new Role("ROLE_user");
            roleUser.setClient(user);
        }

        if (adminParam != null) {
            user.setRole(new HashSet<>());
            user.getRole().add(roleUser);
            user.getRole().add(roleAdmin);
        } else if (userParam != null) {
            user.setRole(new HashSet<>());
            user.getRole().add(roleUser);
        }
        userServiceImpl.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String updateUserPage(@RequestParam Long id, ModelMap model) {
        model.addAttribute("user", userServiceImpl.findUserById(id));
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String editCar(@ModelAttribute User user) {
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }

}
