package com.tiy.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
    UserRepository guest;

    @Autowired
    RequestRepository requestRepository;


    @Test

    public void testAddUser() throws Exception {
        Iterable<Guest> allUsers = guest.findAll();
        int size = 0;
        for (Guest guest : allUsers) {
            size++;
        }

        Guest testUser = new Guest("Maurice", "Thomas", "Iron Yard", "student", "mauricet1520@gmail.com", "password", null, false);
        guest.save(testUser);

        assertNotNull(testUser.getGuestId());

        guest.delete(testUser);

        System.out.println("user" + testUser.getGuestId());


        allUsers = guest.findAll();
        int deleteSize = 0;
        for (Guest guest : allUsers) {
            deleteSize++;
        }

        assertEquals(size, deleteSize);
    }

    @Autowired
    EventRepository event;

    @Test
    public void testAddEvent() throws Exception {
        event.deleteAll();
        Iterable<MyEvent> allEvents = event.findAll();
        int size = 0;
        for (MyEvent event : allEvents) {
            size++;
        }

        System.out.println("the size is "+size);
//        Event testEvent = new Event(null, "The Iron yard", "Atlanta", "downtown", java.sql.Timestamp.valueOf(LocalDateTime.now()));
//        event.save(testEvent);
//        assertNotNull(testEvent.getEvent_id());


        MyEvent testSecondEvent = new MyEvent("The UnderGround", "Atlanta", "downtown",
                java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.JANUARY),20,12,5)));
        event.save(testSecondEvent);
        System.out.println(testSecondEvent.getMyEventId());
        assertNotNull(testSecondEvent.getMyEventId());

        Iterable<MyEvent> allTheEvents = event.findAll();
        int newSize = 0;
        for (MyEvent event : allTheEvents) {
            newSize++;
        }
        System.out.println("the newsize is "+newSize);


        assertEquals(size+1, newSize);


        MyEvent timeEvent = testSecondEvent;

        java.sql.Timestamp myTime = java.sql.Timestamp.valueOf(LocalDateTime.now());

        assertEquals(timeEvent.getTime(), testSecondEvent.getTime());
        event.delete(testSecondEvent);


    }

    @Test
    public void testImage(){
        Guest file = new Guest();
        file.setImage("unit-test-file.jpg");
        try {
            String content = new Scanner(new File("test.txt")).useDelimiter("\\Z").next();
            System.out.println("Content length: " + content.length());
            file.setImage(content);


        } catch (Exception exception) {
            System.out.println("Unable to read file with exception: " + exception.getMessage());
        }
    }

    @Test
    public void testTablesJoined(){
        event.deleteAll();
        guest.deleteAll();

        Set<Guest> myGuests = new HashSet<>();
        Set<MyEvent> myEvents = new HashSet<>();

        MyEvent secondEvent;
        MyEvent currentEvent = new MyEvent();
        Guest currentGuest  = new Guest();
        Guest theGuest;

        currentEvent.setEventName("The Iron Yard");
        currentEvent.setLocation("Atl");
        currentEvent.setAddress("somewhere");

        currentGuest.setFirstName("Maurice");
        currentGuest.setCompany("Iron Yard");
        currentGuest.setLastName("Thomas");
        currentGuest.setPosition("student");
        currentGuest.setPassword("password");

        myEvents.add(currentEvent);
        myGuests.add(currentGuest);

        currentEvent.setGuests(myGuests);
        currentGuest.setMyEvents(myEvents);

        theGuest = guest.save(currentGuest);

        assertEquals(theGuest.getFirstName(), currentGuest.getFirstName());
        assertNotNull(theGuest.getGuestId());

        secondEvent = event.save(currentEvent);

        assertNotNull(secondEvent.getMyEventId());
        assertEquals(secondEvent.getEventName(), currentEvent.getEventName());

        assertEquals(theGuest.getMyEvents().size(), currentGuest.getMyEvents().size());

        assertEquals(secondEvent.getGuests().size(), currentEvent.getGuests().size());

        event.deleteAll();
        guest.deleteAll();

//        event.delete(secondEvent.getMyEventId());
//        guest.delete(theGuest.getGuestId());

    }

    @Test
    public void testOneToMany() {
        guest.deleteAll();

        Guest currentGuest = new Guest();
        Guest otherGuest = new Guest();

        Guest checkGuest1;
        Guest checkGuest2;
        ContactRequest request = new ContactRequest();
        ContactRequest checkRequest;

        currentGuest.setFirstName("Maurice");
        currentGuest.setCompany("Iron Yard");
        currentGuest.setLastName("Thomas");
        currentGuest.setPosition("student");
        currentGuest.setPassword("password");

        otherGuest.setFirstName("Roger");
        otherGuest.setCompany("Iron Yard");
        otherGuest.setLastName("Curtis");
        otherGuest.setPosition("student");
        otherGuest.setPassword("password");

        Set<ContactRequest> contactRequests = new HashSet<>();

        request.setFromUser(currentGuest.getFirstName());
        request.setToUser(currentGuest.getFirstName());
        request.setRequestStatus("pending");

        contactRequests.add(request);

        currentGuest.setContactRequests(contactRequests);
        otherGuest.setContactRequests(contactRequests);

        checkRequest = requestRepository.save(request);
        checkGuest1= guest.save(currentGuest);
       checkGuest2 =  guest.save(otherGuest);

       assertNotNull(checkGuest1.getGuestId());
       assertNotNull(checkGuest2.getGuestId());
       assertNotNull(checkRequest.getMyRequestId());

       assertNotNull(checkGuest1.getContactRequests());
       assertNotNull(checkGuest2.getContactRequests());

        requestRepository.delete(request);
        guest.delete(currentGuest);
        guest.delete(otherGuest);

//        guest.deleteAll();
//        requestRepository.deleteAll();



    }




    }
