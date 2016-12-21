package com.sda.service;

import java.util.List;
import java.util.Optional;

import com.sda.model.User;

public interface UserService {

    User findById(int id);
    
    Optional<User> findByLogin(String login);

    void save(User user);

    void deleteById(int id);

    List<User> listAll();
    
    void update(User user);
}
