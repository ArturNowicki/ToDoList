package com.sda;

import static org.testng.Assert.assertEquals;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.sda.config.HibernateConfig;
import com.sda.dao.UserDao;
import com.sda.dao.UserDaoImpl;
import com.sda.model.User;

public class ToDoListApplication {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		UserDao userDao = new UserDaoImpl();
		String login = "czarek";
		User user = new User(login);
		userDao.saveUser(user);

		User user1 = userDao.findByLogin("czarek");

		assertEquals(user, user1);

		context.close();

	}
}
