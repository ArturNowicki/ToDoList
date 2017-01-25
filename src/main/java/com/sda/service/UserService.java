package com.sda.service;

import java.util.List;
import java.util.Optional;

import com.sda.dto.UserDto;
import com.sda.persistence.model.User;

public interface UserService {

    User findById(int id);
    
    void save(User user);

    void deleteById(int id);

    List<User> listAll();
    
    void update(UserDto user);
    
    Optional<User> findByLogin(String login);
    
	Optional<User> findByEmail(String email);

	boolean isUserUnique(String login);

	void createPasswordResetTokenForUser(User user, String token);

	UserDto getAsDto(int id);

}
