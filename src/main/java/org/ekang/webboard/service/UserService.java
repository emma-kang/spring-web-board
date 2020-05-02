package org.ekang.webboard.service;

import org.ekang.webboard.models.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void addUser(Users u);
    void updateUser(Users u);
    List<Users> listUsers();
    Users getUserById(int id);
    void removeUser(int id);
    Integer getUserIdByName(String name);
}
