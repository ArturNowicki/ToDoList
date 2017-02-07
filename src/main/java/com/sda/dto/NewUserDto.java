package com.sda.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.sda.enums.UserProfileType;
import com.sda.persistence.model.UserName;

public class NewUserDto {

	@Size(min = 3, max = 50)
	@NotNull
	@UserName
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

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserProfileType getUserType() {
		return userType;
	}

	public void setUserType(UserProfileType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "NewUserDto [login=" + login + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", email=" + email + ", userType=" + userType + "]";
	}
	
}
