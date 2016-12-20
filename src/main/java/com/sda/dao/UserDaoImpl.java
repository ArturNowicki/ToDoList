package com.sda.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sda.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao{

	@Override
	public Optional<User> findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveUser(User user) {
		persist(user);
	}

	@Override
	public boolean deleteUserById(int id) {
		Optional<User> maybeUser = getByKey(id);
		if(maybeUser.isPresent()) {
			delete(maybeUser.get());
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

	@Override
	public User findByLogin(String login) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		return (User) criteria.uniqueResult();
	}
}
