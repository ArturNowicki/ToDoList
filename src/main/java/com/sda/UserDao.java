package com.sda;

import java.util.List;

import com.sda.model.User;

public interface UserDao {
    User findById(int id);
    
    User findByLogin(String login);

    void saveUser(User user);

    void deleteUserById(int id);

    List<User> findAllUsers();
}
