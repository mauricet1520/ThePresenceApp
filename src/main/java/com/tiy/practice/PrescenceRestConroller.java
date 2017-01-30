package com.tiy.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by crci1 on 1/25/2017.
 */
@RestController
public class PrescenceRestConroller {

    @Autowired
    RequestRepository theRequest;


    @Autowired
    UserRepository guests;

    @Autowired
    EventRepository events;
    Set<Guest> listOfGuest;
    Set<MyEvent> listOfEvents;

    @RequestMapping(path = "/get_user.json", method = RequestMethod.GET)
    public List<Guest> get_user() {
        List<Guest> userList = new ArrayList<>();
//        guests.deleteAll();

        Guest theGuest = new Guest("Maurice", "Thomas", "Iron Yard", "student", "mauricet1520@gmail.com", "password", null, false);
        Guest secondGuest = new Guest("Roger", "Craig", "IBM", "manager", "music@gmail.com", "password", null, false);
        guests.save(theGuest);
        guests.save(secondGuest);

        Iterable<Guest> allUsers = guests.findAll();
        for (Guest guest : allUsers) {
            userList.add(guest);
        }
        return userList;
    }

    @RequestMapping(path = "/add_user.json", method = RequestMethod.POST)
    public Guest add_user(@RequestBody Guest guest) {
//        guests.deleteAll();

        guests.save(guest);
        return guest;
    }

    @RequestMapping(path = "/add_event.json", method = RequestMethod.POST)
    public Event add_event(@RequestBody Event theEvent) {

        return theEvent;
    }

    @RequestMapping(path = "/get_events.json", method = RequestMethod.GET)
    public List<MyEvent> get_events() {
        List<MyEvent> eventList = new ArrayList<>();
//        events.deleteAll();
//
        MyEvent theEvent = new MyEvent("Iron Pints", "Iron Yard", "Atlanta", java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.JANUARY), 28, 1, 5)));
        MyEvent secondEvent = new MyEvent("Java Crash Course", "Iron Yard", "Atlanta", java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.JANUARY), 29, 8, 5)));
        MyEvent thirdEvent = new MyEvent("PTA meeting", "Dacula High School", "Dacula", java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.MARCH), 20, 7, 5)));
//
        events.save(theEvent);
        events.save(secondEvent);
        events.save(thirdEvent);

//        eventList.add(theEvent);
//        eventList.add(secondEvent);
//        eventList.add(thirdEvent);

        Iterable<MyEvent> allEvents = events.findAll();
        for (MyEvent event : allEvents) {
            eventList.add(event);
        }
        return eventList;

    }

    @RequestMapping(path = "/check_in_event.json", method = RequestMethod.POST)
    public void checkInEvent(@RequestBody EventPretend pretend) {

        Guest currentGuest = guests.findByEmail(pretend.getEmail());
        MyEvent theEvent = events.findByEventName(pretend.getEventName());

        listOfGuest = new HashSet<>();
        listOfEvents = new HashSet<>();
        listOfGuest.add(currentGuest);
        listOfEvents.add(theEvent);
        theEvent.setGuests(listOfGuest);
        currentGuest.setMyEvents(listOfEvents);

        events.save(theEvent);
        guests.save(currentGuest);

    }

    @RequestMapping(path = "/request_contact", method = RequestMethod.POST)
    public void requestContact(@RequestBody EventPretend pretend) {

        Guest toUser = guests.findByEmail(pretend.getEmail());
        Guest fromUser = guests.findByFirstName(pretend.getFirstName());

        ContactRequest request = new ContactRequest();

        request.setRequestStatus("pending");
        request.setToUser(toUser.getFirstName());
        request.setFromUser(fromUser.getFirstName());

        Set<ContactRequest> contactRequests = new HashSet<>();
        contactRequests.add(request);

        fromUser.setContactRequests(contactRequests);

        for (ContactRequest request1 : fromUser.getContactRequests()) {
            System.out.println(request1.getFromUser());
            System.out.println(request1.getToUser());

        }

//            System.out.println(String.valueOf(fromUser.getContactRequests()));

//            guests.save(toUser);
//            guests.save(fromUser);
//            theRequest.save(request);


    }


//    @RequestMapping(path = "/check_in_event.json", method = RequestMethod.POST)
//    public List<MyEvent> checkIn() {
//
//        MyEvent event = new MyEvent();
//
//        List<MyEvent> eventList = new ArrayList<>();
//
//        Guest guest = new Guest();
//        guest.setFirstName("Maurice");
//        Guest guest1 = new Guest();
//        guest1.setFirstName("Thomas");
//        Set<Guest> allGuest = new HashSet<>();
//        allGuest.add(guest);
//        allGuest.add(guest1);
//        event.setGuests(allGuest);
//        event.setEventName("Football Game");
//        event.setAddress("Atlanta");
//        event.setLocation("Back Yard");
//        event.setTime(java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.MARCH),20,7,5)));
//
//        eventList.add(event);
//
//        Iterable<MyEvent> allEvents = events.findAll();
//        for (MyEvent theEvent : allEvents) {
//            eventList.add(theEvent);
//        }
//
//        return eventList;    }

}
