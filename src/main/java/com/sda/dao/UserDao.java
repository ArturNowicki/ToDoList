package com.sda.dao;

import java.util.List;

import com.sda.model.User;

public interface UserDao {
    User findById(int id);

    void saveUser(User user);

    void deleteUserById(int id);

    List<User> findAllUsers();
}
