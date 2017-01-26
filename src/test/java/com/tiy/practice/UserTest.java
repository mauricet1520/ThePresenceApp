package com.tiy.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by crci1 on 1/26/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Autowired
    UserRepository user;

    @Test

    public void testAddUser() throws Exception {
        Iterable<User> allUsers = user.findAll();
        int size = 0;
        for (User user:allUsers) {
            size++;
        }

        User testUser = new User(null, "Maurice", "Thomas", "Iron Yard", "student", "mauricet1520@gmail.com", "password");
        user.save(testUser);

        assertNotNull(testUser.getId());

        user.delete(testUser);

        System.out.println("user" + testUser.getId());



        allUsers = user.findAll();
        int deleteSize = 0;
        for (User user: allUsers) {
            deleteSize++;
        }

        assertEquals(size, deleteSize);
    }
}