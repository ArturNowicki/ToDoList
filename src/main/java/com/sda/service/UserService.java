package com.sda.service;

import java.util.List;
import java.util.Optional;

import com.sda.model.User;

public interface UserService {

    Optional<User> findById(int id);
    
    User findByLogin(String login);

    void saveUser(User user);

    boolean deleteUserById(int id);

    List<User> findAllUsers();
    
    void updateUser(User user);
}
