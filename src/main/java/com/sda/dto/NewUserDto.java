package com.sda.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.sda.enums.UserProfileType;

public class NewUserDto {

	@Size(min = 3, max = 50)
	@NotNull
//	@UserName
	private String login;
	
	@Size(min = 6, max = 100)
	@NotNull
	private String password;

	@Size(min = 6, max = 100)
	@NotNull
	private String confirmPassword;

	@Email
	private String email;

	private UserProfileType userType;

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public UserProfileType getUserType() {
		return userType;
	}
	
	
}
