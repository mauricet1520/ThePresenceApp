package com.tiy.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        public Event add_event(@RequestBody Event event) {
        events.save(event);

        return event;
    }

    @RequestMapping(path = "/get_events.json", method = RequestMethod.GET)
    public List<Event> get_events() {
        List<Event> eventList = new ArrayList<>();

        Iterable<Event> allEvents = events.findAll();
        for (Event event : allEvents) {
            eventList.add(event);
        }
        return eventList;

    }

}
