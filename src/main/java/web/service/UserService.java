package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user);
    List<User> listUsers();
    User findUserByName(String username);
    User findUserById(Long id);
    void updateUser(User user);
    boolean checkUser(User user);
    void deleteUser(Long id);
}