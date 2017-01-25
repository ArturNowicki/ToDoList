package com.sda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sda.dto.UserDto;
import com.sda.persistence.dao.PasswordResetTokenDao;
import com.sda.persistence.dao.UserDao;
import com.sda.persistence.model.PasswordResetToken;
import com.sda.persistence.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordResetTokenDao passwordResetTokenDao;
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public UserDto getAsDto(int id) {
		User user = findById(id);
		return new UserDto(user.getId(), user.getLogin(), user.getEmail(), user.getUserType());
	}
	
	@Override
	public Optional<User> findByLogin(String login) {
		return dao.findByLogin(login);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.add(user);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public List<User> listAll() {
		return dao.listAll();
	}

	@Override
	public void update(UserDto user) {
		User entity = dao.findById(user.getId());
		if(null != entity) {
			entity.setLogin(user.getLogin());
			entity.setEmail(user.getEmail());
			entity.setUserType(user.getUserType());
		}
	}

	@Override
	public boolean isUserUnique(String login) {
		Optional<User> maybeUser = dao.findByLogin(login);
		if(maybeUser.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken resetToken = new PasswordResetToken(token, user);
		passwordResetTokenDao.save(resetToken);
	}
	
	

}
