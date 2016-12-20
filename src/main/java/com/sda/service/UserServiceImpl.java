package com.sda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sda.dao.UserDao;
import com.sda.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao dao;

	@Override
	public Optional<User> findById(int id) {
		return dao.findById(id);
	}

	@Override
	public User findByLogin(String login) {
		return dao.findByLogin(login);
	}

	@Override
	public void saveUser(User user) {
		dao.saveUser(user);
	}

	@Override
	public boolean deleteUserById(int id) {
		return dao.deleteUserById(id);
	}

	@Override
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
