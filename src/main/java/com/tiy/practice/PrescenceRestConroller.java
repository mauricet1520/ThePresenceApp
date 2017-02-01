package com.tiy.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

/**
 * Created by crci1 on 1/25/2017.
 */
@RestController
public class PrescenceRestConroller {

    @Autowired
    RequestRepository theRequest;

    @Autowired
    GuestRepository guests;

    @Autowired
    MyEventRepository events;

    Set<Guest> listOfGuest = new HashSet<>();
    Set<MyEvent> listOfEvents = new HashSet<>();

    @PostConstruct
    public void initializeDB() {
        if (events.count() == 0) {
            System.out.println("Initializing the DB ...");
            // create test events
            MyEvent theEvent = new MyEvent("Iron Pints", "Iron Yard", "Atlanta", java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.JANUARY), 28, 1, 5)));
            MyEvent secondEvent = new MyEvent("Java Crash Course", "Iron Yard", "Atlanta", java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.JANUARY), 29, 8, 5)));
            MyEvent thirdEvent = new MyEvent("PTA meeting", "Dacula High School", "Dacula", java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.MARCH), 20, 7, 5)));
            events.save(theEvent);
            events.save(secondEvent);
            events.save(thirdEvent);
            System.out.println("Initial events added!");

            // create test guests
            Guest theGuest = new Guest("Maurice", "Thomas", "Iron Yard", "student", "mauricet1520@gmail.com", "password", null, false);
            Guest secondGuest = new Guest("Roger", "Craig", "IBM", "manager", "music@gmail.com", "password", null, false);
            guests.save(theGuest);
            guests.save(secondGuest);
            System.out.println("Initial guests added!");
            System.out.println("Done initializing!");
        } else {
            System.out.println("DB already initialized!");
        }
    }

    @RequestMapping(path = "/get_all_requests.json", method = RequestMethod.GET)
    public Guest getAllRequests(String guestEmailAddress) {
        Guest guest = guests.findByEmail(guestEmailAddress);
        List<ContactRequest> requests = new ArrayList<ContactRequest>();
        for (ContactRequest request : guest.getContactRequests()) {
            requests.add(request);
        }

        return guest;
    }

    @RequestMapping(path = "/reject_status_request.json", method = RequestMethod.POST)
    public StatusMessage rejectRequestStatus(@RequestBody RequestContactByEmail requestContactByEmail){
        Guest requester = guests.findByEmail(requestContactByEmail.getRequesterEmail());
        Guest requestee = guests.findByEmail(requestContactByEmail.getRequesterEmail());
        List<ContactRequest> requests = new ArrayList<ContactRequest>();
        for (ContactRequest request : requestee.getContactRequests()) {

            if (request.getRequesterEmailAddress().equals(requester.getEmail())){
                request.setRequestStatus("Rejected");
                theRequest.save(request);
            }
        }

        return new StatusMessage(true, null);
    }

    @RequestMapping(path = "/accept_status_request.json", method = RequestMethod.POST)
    public StatusMessage acceptRequestStatus(@RequestBody RequestContactByEmail requestContactByEmail){
        Guest requester = guests.findByEmail(requestContactByEmail.getRequesterEmail());
        Guest requestee = guests.findByEmail(requestContactByEmail.getRequesterEmail());
        List<ContactRequest> requests = new ArrayList<ContactRequest>();
        for (ContactRequest request : requestee.getContactRequests()) {

            if (request.getRequesterEmailAddress().equals(requester.getEmail())){
                request.setRequestStatus("Accepted");
                theRequest.save(request);
            }
        }

        return new StatusMessage(true, null);
    }

    @RequestMapping(path = "/get_user.json", method = RequestMethod.GET)
    public List<Guest> get_user() {
        List<Guest> userList = new ArrayList<>();

        Iterable<Guest> allUsers = guests.findAll();
        for (Guest guest : allUsers) {
            userList.add(guest);
        }
        return userList;
    }

    @RequestMapping(path = "/add_user.json", method = RequestMethod.POST)
    public Guest add_user(@RequestBody EventCheckinRequest request) {

        Guest currentGuest = new Guest(request.getFirstName(), request.getLastName(), request.getCompany()
                ,request.getPosition(), request.getEmail(), request.getPassword(), null, false);

        guests.save(currentGuest);
        return currentGuest;
    }

    @RequestMapping(path = "/add_event.json", method = RequestMethod.POST)
    public MyEvent add_event(@RequestBody Event theEvent) {
        MyEvent currentEvent = new MyEvent(theEvent.getName(), theEvent.getLocation(), theEvent.getAddress(), null);
        events.save(currentEvent);

        return currentEvent;
    }

    @RequestMapping(path = "/get_events.json", method = RequestMethod.GET)
    public List<MyEvent> get_events() {
        List<MyEvent> eventList = new ArrayList<>();

        Iterable<MyEvent> allEvents = events.findAll();
        for (MyEvent event : allEvents) {
            eventList.add(event);
        }
        return eventList;

    }

    // works on localhost not heroku
    @RequestMapping(path = "/check_in_event.json", method = RequestMethod.POST)
    public MyEvent checkInEvent(@RequestBody EventCheckinRequest checkinRequest) {

        Guest currentGuest = guests.findByEmail(checkinRequest.getGuestEmail());
        MyEvent theEvent = events.findByEventName(checkinRequest.getEventName());

        theEvent.getGuests().add(currentGuest);
        events.save(theEvent);
//
        return theEvent;

    }

    @RequestMapping(path = "/request_contact", method = RequestMethod.POST)
    public StatusMessage requestContact(@RequestBody RequestContactByEmail requestContactByEmail) {

        Guest requester = guests.findByEmail(requestContactByEmail.getRequesterEmail());
        Guest requestee = guests.findByEmail(requestContactByEmail.getRequesteeEmail());

        ContactRequest request = new ContactRequest();
        request.setRequesterEmailAddress(requester.getEmail());
        request.setRequesteeEmailAddress(requestee.getEmail());
        request.setRequestStatus("pending");
        request.setGuest(requester);
        theRequest.save(request);

        Iterator<ContactRequest> requestsIterator = theRequest.findAll().iterator();
        while (requestsIterator.hasNext()) {
            ContactRequest contactRequest = requestsIterator.next();
            System.out.println("===========================");
            System.out.println("Contact Requester = " + contactRequest.getRequesterEmailAddress());
            System.out.println("Contact Requestee = " + contactRequest.getRequesteeEmailAddress());
            System.out.println("===========================");
        }

        return new StatusMessage(true, null);
    }

    @RequestMapping(path = "/request_contact_old", method = RequestMethod.POST)
    public String requestContactOld(@RequestBody RequestContactByEmail requestContactByEmail) {

        Guest fromUser = guests.findByEmail(requestContactByEmail.getRequesterEmail());
        Guest toUser = guests.findByEmail(requestContactByEmail.getRequesteeEmail());

        ContactRequest request = new ContactRequest();

        request.setRequestStatus("pending");
        request.setRequesteeEmailAddress(toUser.getFirstName());
        request.setRequesterEmailAddress(fromUser.getFirstName());

//        Set<ContactRequest> contactRequests = new HashSet<>();
//        contactRequests.add(request);

//        toUser.getContactRequests().add(request);
        theRequest.save(request);
        guests.save(fromUser);
        guests.save(toUser);
        ContactRequest statusRequest = theRequest.findByRequesterEmailAddress(fromUser.getFirstName());

//        for (ContactRequest request1 : toUser.getContactRequests()) {
//            System.out.println(request1.getRequesterEmailAddress());
//            System.out.println(request1.getRequesteeEmailAddress());
//
//        }
        String status = null;

        if(statusRequest != null){
            status = "success";
        }else {
            status = "error";
        }

        return status;

//            System.out.println(String.valueOf(fromUser.getContactRequests()));

//            guests.save(toUser);
//            guests.save(fromUser);
//            theRequest.save(request);
    }

    @RequestMapping(path = "/login_user.json", method = RequestMethod.POST)
    public Guest login(@RequestBody RequestContactByEmail request) {
        Guest currentGuest = guests.findByEmail(request.getEmail());
        return currentGuest;
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
