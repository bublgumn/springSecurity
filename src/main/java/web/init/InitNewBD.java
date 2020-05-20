package web.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.ArrayList;


public class InitNewBD {

    public static void addAdmins(UserService userService) {
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_admin");
        roleAdmin.setClient(new ArrayList<>());

        Role roleUser = new Role();
        roleUser.setName("ROLE_user");
        roleUser.setClient(new ArrayList<>());

        User userAdminOne = new User();
        userAdminOne.setUsername("1a");
        userAdminOne.setPassword("1a");
        userAdminOne.setRole(new ArrayList<>());

        userAdminOne.getRole().add(roleAdmin);
        userAdminOne.getRole().add(roleUser);
        roleAdmin.getClient().add(userAdminOne);
        roleUser.getClient().add(userAdminOne);
        userService.add(userAdminOne);

        User userAdminTwo = new User();
        userAdminTwo.setUsername("2a");
        userAdminTwo.setPassword("2a");
        userAdminTwo.setRole(new ArrayList<>());
        userAdminTwo.getRole().add(roleAdmin);
        userAdminTwo.getRole().add(roleUser);
        userService.add(userAdminTwo);
    }

    public static void addUser(UserService userService, RoleService roleService){
        Role roleUser = roleService.findRoleByName("ROLE_user");

        User userOne = new User();
        userOne.setUsername("1u");
        userOne.setPassword("1u");
        userOne.setRole(new ArrayList<>());
        userOne.getRole().add(roleUser);

        User userTwo = new User();
        userTwo.setUsername("2u");
        userTwo.setPassword("2u");
        userTwo.setRole(new ArrayList<>());
        userTwo.getRole().add(roleUser);

        User userThree = new User();
        userThree.setUsername("3u");
        userThree.setPassword("3u");
        userThree.setRole(new ArrayList<>());
        userThree.getRole().add(roleUser);

        userService.add(userOne);
        userService.add(userTwo);
        userService.add(userThree);

    }

    public static void printUsers(RoleService roleService){
        Role roleUser = roleService.findRoleByName("ROLE_user");

        for (User user: roleUser.getClient()
        ) {
            System.out.println(user.getUsername());
        }
    }

}
