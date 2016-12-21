package com.sda.dao;

import java.util.List;
import java.util.Optional;

import com.sda.model.User;

public interface UserDao {

	User findById(int id);

	Optional<User> findByLogin(String login);

	void add(User user);

	void deleteById(int id);

	List<User> listAll();
}
