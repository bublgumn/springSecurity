package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> listRoles() {
        TypedQuery<Role> query = sessionFactory.getCurrentSession().createQuery("from Role");
        return query.getResultList();
    }

    @Override
    public Role findRoleByName(String name) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from Role where name=:rolenameNow");
        query.setParameter("rolenameNow", name);
        return (Role) query.getSingleResult();
    }

    @Override
    public Role findRoleById(Long id) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from Role r where r.id=:roleId");
        query.setParameter("roleId", id);
        return (Role) query.getSingleResult();
    }

    @Override
    public void updateRole(Role role) {
        sessionFactory.getCurrentSession().update(role);
    }

    @Override
    public boolean checkRole(Role role) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Role where name=:rolenameNow")
                .setParameter("rolenameNow", role.getName());
        return query.getResultList().isEmpty();
    }

}
