package com.tiy.practice;

/**
 * Created by crci1 on 1/28/2017.
 */
public class EventPretend {


    public EventPretend() {
    }

    public EventPretend(String email, String eventName) {
        this.email = email;
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


}
