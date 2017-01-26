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

        

//        if (firstName != null) {
//            userList = users.findByFirstName(firstName);
//        }
//
//        if (lastName != null) {
//            userList = users.findByLastName(lastName);
//        }
//
//        if (company != null) {
//            userList = users.findByCompany(company);
//        }
//
//        if (position != null) {
//            userList = users.findByPosition(position);
//        }
//
//        if (email != null) {
//            userList = users.findByEmail(email);
//        }
//
//        if (password != null) {
//            userList = users.findByPassword(password);
//
//        }
        users.save(user);
        return user;
    }

}
