package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.init.InitNewBD;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
@ComponentScan(value = "web")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    /*@GetMapping(value = "/create")
    public String loginCreate() {
        InitNewBD.addAdmins(userService);
        InitNewBD.addUser(userService, roleService);
        InitNewBD.printUsers(roleService);
        *//*deleteTest();*//*
        *//*findUser();*//*
        return "login";
    }*/


    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/login";
    }

}