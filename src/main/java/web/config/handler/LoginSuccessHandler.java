package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component("successHandlerNew")
@Transactional
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        Set<String> setRole = new HashSet<>();
        for (Role role : user.getRole()
        ) {
            setRole.add(role.getName());
        }
        if (setRole.contains("ROLE_admin")) {
            httpServletResponse.sendRedirect("/admin/");
        } else if (setRole.contains("ROLE_user")) {
            httpServletResponse.sendRedirect("/user/");
        }
    }
}