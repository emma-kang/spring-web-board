package org.ekang.webboard.dao;

import org.ekang.webboard.models.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO {
    private static final Logger logger = LoggerFactory.getLogger(UsersDAOImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addUser(Users u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(u);
        logger.info("User added successfully : " + u);
    }

    @Override
    public void updateUser(Users u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(u);
        logger.info("User updated successfully :" + u);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Users> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Users> userList = session.createQuery("from Users").list();
        for(Users u : userList) {
            logger.info("Users List: " + u);
        }
        return userList;
    }

    @Override
    public Users getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Users u = (Users) session.load(Users.class, new Integer(id));
        logger.info("Users loaded successfully : " + u);
        return u;
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Users u = (Users) session.load(Users.class, new Integer(id));
        if(u != null)
            session.delete(u);

        logger.info("User deleted successfully :" + u);
    }

    @Override
    public Users getUserByName(String username) {
        List<Users> users = this.listUsers();

        for (Users user : users) {
            if(user.getUsername().equals(username))
                return user;

        }

        return null;
    }

}
