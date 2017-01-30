package com.tiy.practice;

/**
 * Created by crci1 on 1/30/2017.
 */
public class RequestGuest {

    private String firstName;
    private String email;

    public RequestGuest () {

    }

    public RequestGuest(String toUser, String email) {
        this.firstName = toUser;
        this.email = email;
    }

    public String firstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
