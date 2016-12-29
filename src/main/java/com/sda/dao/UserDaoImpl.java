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
	public User findById(int id) {
		return getByKey(id);
	}

	@Override
	public void add(User user) {
		persist(user);
	}

	@Override
	public void deleteById(int id) {
		User user = getByKey(id);
		delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listAll() {
		Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

	@Override
	public Optional<User> findByLogin(String login) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		return Optional.ofNullable((User) criteria.uniqueResult());
	}
}
