package com.sda.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.sda.enums.UserProfileType;

@Entity
@Table(name = "User", uniqueConstraints = { @UniqueConstraint(columnNames = { "login" }) })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true, length = 11)
	private int id;

	@Size(min = 3, max = 50)
	@NotNull
	@UserName
	@Column(name = "login", nullable = false, unique = true, length = 50)
	private String login;

	@Size(min = 6, max = 50)
	@NotNull
	@Column(name = "pass", nullable = false, length = 50)
	private String password;
	
	@Email
	@Column(name = "email", length = 50)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", length = 50)
	private UserProfileType userType;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assignedUser")
	private List<Item> ownedItemsList = new ArrayList<Item>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	private List<Item> createdItemsList = new ArrayList<Item>();

	public User() {
	}

	public User(String login) {
		this.login = login.toLowerCase();
	}

//	getters&setters
	
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
		this.login = login.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Item> getOwnedItemsList() {
		return ownedItemsList;
	}

	public void setOwnedItemsList(List<Item> ownedItemsList) {
		this.ownedItemsList = ownedItemsList;
	}

	public List<Item> getCreatedItemsList() {
		return createdItemsList;
	}

	public void setCreatedItemsList(List<Item> createdItemsList) {
		this.createdItemsList = createdItemsList;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", email=" + email + ", userType=" + userType + "]";
	}



}
