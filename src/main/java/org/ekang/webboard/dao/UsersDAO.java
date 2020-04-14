package org.ekang.webboard.dao;

import org.ekang.webboard.models.Users;

import java.util.List;

public interface UsersDAO {
    public void addUser(Users u);
    public void updateUser(Users u);
    public List<Users> listUsers();
    public Users getUserById(int id);
    public void removeUser(int id);
}
