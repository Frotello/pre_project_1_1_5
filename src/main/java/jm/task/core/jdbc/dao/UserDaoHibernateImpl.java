package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction tx = null;


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)")
                    .executeUpdate();
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            if (null != tx) {
                tx.rollback();
            }
        }
    }
    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS users")
                    .executeUpdate();
            tx.commit();

        } catch (PersistenceException e) {
            e.printStackTrace();
            if (null != tx) {
                tx.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(new User(name, lastName, (byte) age));
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            if (null != tx) {
                tx.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            if (null != user) {
                session.delete(user);
            }
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users")
                    .executeUpdate();
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            if (null != tx) {
                tx.rollback();
            }
        }
    }
}

