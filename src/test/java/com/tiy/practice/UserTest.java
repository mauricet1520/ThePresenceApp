package com.tiy.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

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
        for (User user : allUsers) {
            size++;
        }

        User testUser = new User(null, "Maurice", "Thomas", "Iron Yard", "student", "mauricet1520@gmail.com", "password");
        user.save(testUser);

        assertNotNull(testUser.getId());

        user.delete(testUser);

        System.out.println("user" + testUser.getId());


        allUsers = user.findAll();
        int deleteSize = 0;
        for (User user : allUsers) {
            deleteSize++;
        }

        assertEquals(size, deleteSize);
    }

    @Autowired
    EventRepository event;

    @Test
    public void testAddEvent() throws Exception {
        event.deleteAll();
        Iterable<Event> allEvents = event.findAll();
        int size = 0;
        for (Event event : allEvents) {
            size++;
        }

        System.out.println("the size is "+size);
//        Event testEvent = new Event(null, "The Iron yard", "Atlanta", "downtown", java.sql.Timestamp.valueOf(LocalDateTime.now()));
//        event.save(testEvent);
//        assertNotNull(testEvent.getId());


        Event testSecondEvent = new Event(null, "The UnderGround", "Atlanta", "downtown",
                java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.JANUARY),20,12,5)));
        event.save(testSecondEvent);
        System.out.println(testSecondEvent.getId());
        assertNotNull(testSecondEvent.getId());

        Iterable<Event> allTheEvents = event.findAll();
        int newSize = 0;
        for (Event event : allTheEvents) {
            newSize++;
        }
        System.out.println("the newsize is "+newSize);


        assertEquals(size+1, newSize);


        Event timeEvent = testSecondEvent;

        java.sql.Timestamp myTime = java.sql.Timestamp.valueOf(LocalDateTime.now());

        assertEquals(timeEvent.getTime(), testSecondEvent.getTime());
        event.delete(testSecondEvent);


    }


    }
