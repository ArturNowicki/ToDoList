package com.sda.service;

import java.util.List;
import java.util.Optional;

import com.sda.dto.EditUserDto;
import com.sda.persistence.model.User;

public interface UserService {

    User findById(int id);
    
    void save(User user);

    void deleteById(int id);

    List<User> listAll();
    
    void update(EditUserDto user);
    
    void changePassword(User user, String password);
    
    Optional<User> findByLogin(String login);
    
	Optional<User> findByEmail(String email);

	boolean isUserUnique(String login);

	boolean isPasswordMatching(String password, String confirmPassowrd);

	void createPasswordResetTokenForUser(User user, String token);

	EditUserDto getAsDto(int id);

}
