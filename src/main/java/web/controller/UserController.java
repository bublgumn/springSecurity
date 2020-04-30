package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@ComponentScan(value = "web")
public class UserController {
    @Autowired
    private UserService userServiceImpl;

    @GetMapping
    public String printWelcome(ModelMap model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<String> messages = new ArrayList<>();
        messages.add("Welcome " + user.getUsername() + "!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }
}
