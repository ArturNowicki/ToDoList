package com.sda.persistence.dao;

import java.util.List;
import java.util.Optional;

import com.sda.persistence.model.User;

public interface UserDao {

	User findById(int id);

	Optional<User> findByLogin(String login);
	
	Optional<User> findByEmail(String email);

	void add(User user);

	void deleteById(int id);

	List<User> listAll();
}
