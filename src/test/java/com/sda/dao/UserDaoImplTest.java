package com.sda.dao;

import com.sda.dao.UserDao;
import com.sda.dao.UserDaoImpl;
import com.sda.model.User;
import org.junit.Test;

import static org.testng.Assert.assertEquals;

public class UserDaoImplTest {

    @Test
    public void addUserToDb() {
        UserDao userDao = new UserDaoImpl();
        String login = "czarek";
        User user = new User(login);
        userDao.saveUser(user);

        User user1 = userDao.findByLogin("czarek");

        assertEquals(user, user1);




    }

}
