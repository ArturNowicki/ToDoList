package com.sda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sda.dto.EditUserDto;
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
	public EditUserDto getAsDto(int id) {
		User user = findById(id);
		return new EditUserDto(user.getId(), user.getLogin(), user.getEmail(), user.getUserType());
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
		user.setPassword(encodePassword(user.getPassword()));
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
	public void update(EditUserDto user) {
		User entity = dao.findById(user.getId());
		if(null != entity) {
			entity.setLogin(user.getLogin());
			entity.setEmail(user.getEmail());
			entity.setUserType(user.getUserType());
		}
	}

	@Override
	public void changePassword(User user, String password) {
		User entity = dao.findById(user.getId());
		if(null != entity) {
			entity.setPassword(encodePassword(password));
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
	public boolean isPasswordMatching(String password, String confirmPassowrd) {
		return password.equals(confirmPassowrd) ? true : false;
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken resetToken = new PasswordResetToken(token, user);
		passwordResetTokenDao.save(resetToken);
	}
	
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

}
