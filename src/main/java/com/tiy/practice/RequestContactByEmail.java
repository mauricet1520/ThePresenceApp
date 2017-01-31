package com.tiy.practice;

/**
 * Created by crci1 on 1/30/2017.
 */
public class RequestContactByEmail {

    private String requesterEmail;
    private String requesteeEmail;

    public RequestContactByEmail() {
    }

    public RequestContactByEmail(String requesterEmail, String requesteeEmail) {
        this.requesterEmail = requesterEmail;
        this.requesteeEmail = requesteeEmail;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    public String getRequesteeEmail() {
        return requesteeEmail;
    }

    public void setRequesteeEmail(String requesteeEmail) {
        this.requesteeEmail = requesteeEmail;
    }

    public RequestContactByEmail(String email) {
        this.requesterEmail = email;
    }

    public String getEmail() {
        return requesterEmail;
    }

    public void setEmail(String email) {
        this.requesterEmail = email;
    }
}
