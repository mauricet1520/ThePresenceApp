package com.tiy.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    GuestRepository guest;

    @Autowired
    RequestRepository requestRepository;

    @Test

    public void testAddUser() throws Exception {

        Guest testGuest = new Guest("Maurice", "Thomas", "Iron Yard", "student", "mauricet1520@gmail.com", "password", null, false);

        Guest currentGuest = new Guest();
        currentGuest.setFirstName("Gayle");
        currentGuest.setCompany("Code Help");
        currentGuest.setLastName("Sanders");
        currentGuest.setPosition("manager");
        currentGuest.setPassword("password");

        // save the guest and check it
        guest.save(currentGuest);
        guest.save(testGuest);
        assertNotNull(currentGuest.getGuestId());
        assertNotNull(testGuest.getGuestId());

        Guest retrievedGuest = guest.findOne(currentGuest.getGuestId());
        assertEquals(retrievedGuest.getFirstName(), currentGuest.getFirstName());
        assertNotNull(retrievedGuest.getGuestId());

        guest.delete(currentGuest);
        guest.delete(testGuest);

//        Iterable<Guest> allGuest = guest.findAll();
//        int size = 0;
//        for (Guest guest : allGuest) {
//            size++;
//        }
//
//
//        guest.save(testGuest);
//
//        assertNotNull(testGuest.getGuestId());
//
//        guest.delete(testGuest);
//
//        System.out.println("user" + testGuest.getGuestId());
//
//        allGuest = guest.findAll();
//        int deleteSize = 0;
//        for (Guest guest : allGuest) {
//            deleteSize++;
//        }
//
//        assertEquals(size, deleteSize);
    }

    @Autowired
    MyEventRepository event;

    @Test
    public void testAddEvent() throws Exception {
        event.deleteAll();
        Iterable<MyEvent> allEvents = event.findAll();
        int size = 0;
        for (MyEvent event : allEvents) {
            size++;
        }

        System.out.println("the size is " + size);
//        Event testEvent = new Event(null, "The Iron yard", "Atlanta", "downtown", java.sql.Timestamp.valueOf(LocalDateTime.now()));
//        event.save(testEvent);
//        assertNotNull(testEvent.getEvent_id());

        MyEvent testSecondEvent = new MyEvent("The UnderGround", "Atlanta", "downtown",
                java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.JANUARY), 20, 12, 5)));
        event.save(testSecondEvent);
        System.out.println(testSecondEvent.getMyEventId());
        assertNotNull(testSecondEvent.getMyEventId());

        Iterable<MyEvent> allTheEvents = event.findAll();
        int newSize = 0;
        for (MyEvent event : allTheEvents) {
            newSize++;
        }
        System.out.println("the newsize is " + newSize);

        assertEquals(size + 1, newSize);

        MyEvent timeEvent = testSecondEvent;

        java.sql.Timestamp myTime = java.sql.Timestamp.valueOf(LocalDateTime.now());

        assertEquals(timeEvent.getTime(), testSecondEvent.getTime());
        event.delete(testSecondEvent);

    }

    @Test
    public void testImage() {
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
    @Ignore
    // need to test the uniqueness of email later
    public void testDuplicateEmail() {
        Guest currentGuest = new Guest();
        currentGuest.setEmail("dup-test-unit-test@gmail.com");
        guest.save(currentGuest);
        Guest duplicateGuest = new Guest();
        duplicateGuest.setEmail("dup-test-unit-test@gmail.com");
        guest.save(duplicateGuest);
    }

    @Test
    public void testRetrieveGuestByEmail() {
        Guest currentGuest = new Guest();
        currentGuest.setFirstName("Maurice");
        currentGuest.setCompany("Iron Yard");
        currentGuest.setLastName("Thomas");
        currentGuest.setPosition("student");
        currentGuest.setPassword("password");
        currentGuest.setEmail("unit-test-user@gmail.com");

        guest.save(currentGuest);

        Guest retrievedGuest = guest.findByEmail(currentGuest.getEmail());
        assertNotNull(retrievedGuest);
        assertEquals(currentGuest.getGuestId(), retrievedGuest.getGuestId());

        guest.delete(currentGuest);
    }

    @Test
    public void testCheckInEvent() {
        // Create data for this test
        MyEvent currentEvent = new MyEvent();
        Guest currentGuest = new Guest();

        currentEvent.setEventName("The Iron Yard");
        currentEvent.setLocation("Atl");
        currentEvent.setAddress("somewhere");

        currentGuest.setFirstName("Maurice");
        currentGuest.setCompany("Iron Yard");
        currentGuest.setLastName("Thomas");
        currentGuest.setPosition("student");
        currentGuest.setPassword("password");

        // save the guest and check it
        guest.save(currentGuest);
        assertNotNull(currentGuest.getGuestId());
        Guest retrievedGuest = guest.findOne(currentGuest.getGuestId());
        assertEquals(retrievedGuest.getFirstName(), currentGuest.getFirstName());
        assertNotNull(retrievedGuest.getGuestId());

        // add the saved guest to the event
        currentEvent.getGuests().add(currentGuest);
        // save the event with the added guest and check it
        currentEvent = event.save(currentEvent);
        assertNotNull(currentEvent.getMyEventId());
        MyEvent retrievedEvent = event.findOne(currentEvent.getMyEventId());
        assertNotNull(retrievedEvent.getMyEventId());
        assertEquals(retrievedEvent.getEventName(), currentEvent.getEventName());
        assertNotNull(retrievedEvent.getGuests());
        assertEquals(1, retrievedEvent.getGuests().size());
        // check that the guest from the event is the same
        // as the guest we created locally
        Guest guestFromEventInDB = retrievedEvent.getGuests().iterator().next();
        assertNotNull(guestFromEventInDB);
        assertEquals(currentGuest.getGuestId(), guestFromEventInDB.getGuestId());

        // test that the guest has the event (i.e. test that the relationship
        // is bidirectional)
        retrievedGuest = guest.findOne(currentGuest.getGuestId());
        assertNotNull(retrievedGuest.getMyEvents());
        assertEquals(1, retrievedGuest.getMyEvents().size());
        MyEvent eventFromGuestInDB = retrievedGuest.getMyEvents().iterator().next();
        assertNotNull(eventFromGuestInDB.getMyEventId());
        assertEquals(currentEvent.getMyEventId(), eventFromGuestInDB.getMyEventId());

        // now let's test adding a second guest to the same event
        Guest secondGuest = new Guest();
        currentGuest.setFirstName("Maurice");
        currentGuest.setCompany("Iron Yard");
        currentGuest.setLastName("Thomas");
        currentGuest.setPosition("student");
        currentGuest.setPassword("password");
        guest.save(secondGuest);

        currentEvent.getGuests().add(secondGuest);
        event.save(currentEvent);
        retrievedEvent = event.findOne(currentEvent.getMyEventId());
        assertEquals(2, retrievedEvent.getGuests().size());

        event.delete(currentEvent);
        guest.delete(currentGuest);

    }

    @Test
    public void testOneToMany() {

        Guest currentGuest = new Guest();
        Guest otherGuest = new Guest();

        ContactRequest request = new ContactRequest();

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

        ContactRequest checkRequest = requestRepository.save(request);
        Guest checkGuest1 = guest.save(currentGuest);
        Guest checkGuest2 = guest.save(otherGuest);

        assertNotNull(checkGuest1.getGuestId());
        assertNotNull(checkGuest2.getGuestId());
        assertNotNull(checkRequest.getMyRequestId());

        assertNotNull(checkGuest1.getContactRequests());
        assertNotNull(checkGuest2.getContactRequests());

        requestRepository.delete(request);
        guest.delete(currentGuest);
        guest.delete(otherGuest);


    }


}
