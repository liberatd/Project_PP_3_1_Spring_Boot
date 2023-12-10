package ru.vologin.springBoot.Project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.vologin.springBoot.Project.model.User;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void add (User user) {
        entityManager.persist(user);
    }

    public void update (User user, long id) {
        user.setId(id);
        entityManager.merge(user);
    }

    public void delete (long id) {
       entityManager.remove(entityManager.find(User.class, id));
    }

    public User getById (long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }
}