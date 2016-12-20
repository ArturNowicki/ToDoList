package com.sda.dao;

import static org.testng.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sda.model.User;

public class UserDaoImplTest {
	
	@Autowired
	UserDao userDao;

    @Test
    public void addUserToDb() {
    	String userName = "czarek";
        String login = userName;
        User user = new User(login);
        userDao.saveUser(user);

        User userFromDB = userDao.findByLogin(userName);

        assertEquals(user, userFromDB);




    }

}
