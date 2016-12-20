package com.sda;

import org.springframework.beans.factory.annotation.Autowired;

import com.sda.dao.UserDao;

public class ToDoListApplication {
	
	@Autowired
	UserDao userDao;

	public static void main(String[] args) {


	}
}
