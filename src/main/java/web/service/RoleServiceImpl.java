package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public List<Role> listRoles() {
        List<Role> result = roleDao.listRoles();
        for (Role role: result
             ) {
            role.getClient().iterator();
        }
        return result;
    }

    @Override
    @Transactional
    public Role findRoleByName(String name) {
        Role result = roleDao.findRoleByName(name);
        result.getClient().iterator();
        return result;
    }

    @Override
    @Transactional
    public Role findRoleById(Long id) {
        Role result = roleDao.findRoleById(id);
        result.getClient().iterator();
        return result;
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    @Transactional
    public boolean checkRole(Role role) {
        return roleDao.checkRole(role);
    }

}
