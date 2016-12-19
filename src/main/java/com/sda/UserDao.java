package com.sda;

import java.util.List;
import java.util.Optional;

import com.sda.model.User;

public interface UserDao {
    Optional<User> findById(int id);
    
    User findByLogin(String login);

    void saveUser(User user);

    boolean deleteUserById(int id);

    List<User> findAllUsers();
}
