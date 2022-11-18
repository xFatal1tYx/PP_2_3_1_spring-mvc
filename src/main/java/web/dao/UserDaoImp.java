package web.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    UserDaoImp() {

    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.saveOrUpdate(user);
        }
    }

    @Override
    public User getUser(int id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.get(User.class, id);
        }
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("delete from User where id = :userId");
        query.setParameter("userId", id).executeUpdate();
    }
}
