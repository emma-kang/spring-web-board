package org.ekang.webboard.service;

import org.ekang.webboard.dao.UsersDAO;
import org.ekang.webboard.models.Users;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UsersDAO usersDAO;

//    public UserServiceImpl(UsersDAO usersDAO) {
//        this.usersDAO = usersDAO;
//    }
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    @Transactional
    public void addUser(Users u) {
        this.usersDAO.addUser(u);
    }

    @Override
    @Transactional
    public void updateUser(Users u) {
        this.usersDAO.updateUser(u);
    }

    @Override
    @Transactional
    public List<Users> listUsers() {
        return this.usersDAO.listUsers();
    }

    @Override
    @Transactional
    public Users getUserById(int id) {
        return this.usersDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.usersDAO.removeUser(id);
    }

}
