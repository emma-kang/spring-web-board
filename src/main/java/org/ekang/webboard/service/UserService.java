package org.ekang.webboard.service;

import org.ekang.webboard.models.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public void addUser(Users u);
    public void updateUser(Users u);
    public List<Users> listUsers();
    public Users getUserById(int id);
    public void removeUser(int id);
}
