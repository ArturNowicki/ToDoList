package com.sda;

import com.sda.model.User;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

public class UserDaoImplTest {

    @Test
    public void addUserToDb() {
        UserDao userDao = new UserDaoImpl();
        String login = "czarek";
        User user = new User(login);
        userDao.saveUser(user);

        userDao.findByLogin("czarek");

    }

}
