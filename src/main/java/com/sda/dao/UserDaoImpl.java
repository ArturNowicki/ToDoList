package com.sda.dao;

import java.util.List;

import com.sda.model.User;

public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{

	@Override
	public User findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveUser(User user) {
		persist(user);
	}

	@Override
	public void deleteUserById(int id) {

	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}


}
