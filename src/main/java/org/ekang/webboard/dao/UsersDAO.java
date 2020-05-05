package org.ekang.webboard.dao;

import org.ekang.webboard.models.Users;

import java.util.List;

public interface UsersDAO {
    void addUser(Users u);
    void updateUser(Users u);
    List<Users> listUsers();
    Users getUserById(int id);
    void removeUser(int id);
    Users getUserByName(String name);
}
