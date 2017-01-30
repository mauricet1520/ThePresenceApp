package com.tiy.practice;

/**
 * Created by crci1 on 1/28/2017.
 */
public class EventCheckinRequest {

    private String guestEmail;
    private String eventName;

    public EventCheckinRequest() {
    }
    public EventCheckinRequest(String guestEmail, String eventName) {
        this.guestEmail = guestEmail;
        this.eventName = eventName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
