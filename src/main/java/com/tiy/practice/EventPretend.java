package com.tiy.practice;

/**
 * Created by crci1 on 1/28/2017.
 */
public class EventPretend {

    public EventPretend() {
    }

    public EventPretend(String email, String eventName, String name, String lastName) {
        this.email = email;
        this.firstName = name;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    String email;
    String eventName;
    String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String lastName;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
