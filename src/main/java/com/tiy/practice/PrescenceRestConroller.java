package com.tiy.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crci1 on 1/25/2017.
 */
@RestController
public class PrescenceRestConroller {

    @Autowired
    UserRepository users;

    @Autowired
    EventRepository events;

    @RequestMapping(path = "/get_user.json", method = RequestMethod.GET)
    public List<User> get_user() {
        List<User> userList = new ArrayList<>();

        Iterable<User> allUsers = users.findAll();
        for (User user : allUsers) {
            userList.add(user);
        }
        return userList;
    }

    @RequestMapping(path = "/add_user.json", method = RequestMethod.POST)
        public User add_user(@RequestBody User user) {


        users.save(user);
        return user;
    }

    @RequestMapping(path = "/add_event.json", method = RequestMethod.POST)
        public Event add_event(@RequestBody Event theEvent) {


        return theEvent;
    }

    @RequestMapping(path = "/get_events.json", method = RequestMethod.GET)
    public List<Event> get_events() {
        Event theEvent = new Event(1, "Iron Pints", "Iron Yard", "Atlanta", java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.JANUARY),28,1,5)));
        Event secondEvent = new Event(2, "Java Crash Course", "Iron Yard", "Atlanta", java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.JANUARY),29,8,5)));
        Event thirdEvent = new Event(3, "PTA meeting", "Dacula High School", "Dacula", java.sql.Timestamp.valueOf(LocalDateTime.of(2017, Month.from(Month.MARCH),20,7,5)));


        List<Event> eventList = new ArrayList<>();

        eventList.add(theEvent);
        eventList.add(secondEvent);
        eventList.add(thirdEvent);

        Iterable<Event> allEvents = events.findAll();
        for (Event event : allEvents) {
            eventList.add(event);
        }
        return eventList;

    }

}
