package com.sda.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.sda.enums.UserProfileType;
import com.sda.persistence.model.UserName;

public class EditUserDto {

	@NotNull
	private int id;
	
	@Size(min = 3, max = 50)
	@NotNull
	@UserName
	private String login;

	@Email
	private String email;

	private UserProfileType userType;

	public EditUserDto() {
	}

	public EditUserDto(int id, String login, String email, UserProfileType userType) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
		return "UserDto [id=" + id + ", login=" + login + ", email=" + email + ", userType=" + userType + "]";
	}



}