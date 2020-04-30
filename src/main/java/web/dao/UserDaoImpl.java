package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public boolean checkUser(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username=:usernameNow")
                .setParameter("usernameNow", user.getUsername());
        return query.getResultList().isEmpty();
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUserByName(String username) {
        Query query =  sessionFactory.getCurrentSession().createQuery("from User where username=:usernameNow");
        query.setParameter("usernameNow", username);
        return (User) query.getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(User.class, id));
    }

    @Override
    public User findUserById(Long id) {
        org.hibernate.query.Query query = sessionFactory.openSession().createQuery("From User u where u.id = :nowId ");
        query.setParameter("nowId", id);
        return (User) query.uniqueResult();
    }
}
