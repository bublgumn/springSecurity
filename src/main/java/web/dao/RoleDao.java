package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleDao {
    List<Role> listRoles();
    Role findRoleByName(String name);
    Role findRoleById(Long id);
    void updateRole(Role role);
    boolean checkRole(Role role);
}
