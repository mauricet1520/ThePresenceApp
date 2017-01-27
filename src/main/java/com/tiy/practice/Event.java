package com.tiy.practice;

import javax.persistence.*;

/**
 * Created by crci1 on 1/27/2017.
 */

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    String address;

    public Event() {
        
    }

    public Event(Integer id, String name, String location, String address) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
