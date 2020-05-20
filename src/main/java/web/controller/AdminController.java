package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;

@Controller
@RequestMapping("/admin")
@ComponentScan(value = "web")
public class AdminController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String printCars(ModelMap model) {
        model.addAttribute("users", userServiceImpl.listUsers());
        return "admin";
    }

    @PostMapping("/newuser")
    public String createUser(@ModelAttribute User user,
                             @RequestParam(required = false, name = "adminparam") String adminParam) {
        Role roleUser;
        Role roleAdmin;

        if (adminParam != null) {
            roleAdmin = roleService.findRoleByName("ROLE_admin");
            roleUser = roleService.findRoleByName("ROLE_user");
            user.setRole(new ArrayList<>());
            user.getRole().add(roleAdmin);
            user.getRole().add(roleUser);
            userServiceImpl.add(user);
        } else {
            roleUser = roleService.findRoleByName("ROLE_user");
            user.setRole(new ArrayList<>());
            user.getRole().add(roleUser);
            userServiceImpl.add(user);
        }
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
