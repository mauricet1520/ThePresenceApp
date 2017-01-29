package com.tiy.practice;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by crci1 on 1/28/2017.
 */
@Entity
public class MyEvent {
    private Long myEventId;
    private String eventName;
    private Set<Guest> guests;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="event_id")
    public Long getMyEventId() {
        return myEventId;
    }

    @Column(name="event_name")
    public String getEventName() {
        return eventName;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(name="guest_event ", joinColumns=@JoinColumn(name="event_id"), inverseJoinColumns=@JoinColumn(name="guest_id"))
    public Set<Guest> getGuests() {
        return guests;
    }

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    String address;

    @Column(nullable = true)
    java.sql.Timestamp time;

    public void setMyEventId(Long myEventId) {
        this.myEventId = myEventId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    MyEvent() {

    }

    public void setGuests(Set<Guest> guests) {
        this.guests = guests;
    }

    public MyEvent(String eventName, String location, String address, Timestamp time) {
        this.eventName = eventName;
        this.location = location;
        this.address = address;
        this.time = time;

    }
}
