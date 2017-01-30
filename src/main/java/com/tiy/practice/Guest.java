package com.tiy.practice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by crci1 on 1/28/2017.
 */

@Entity
public class Guest {
    private Long guestId;
    private String firstName;
    private Set<MyEvent> myEvents;
    private Set<ContactRequest> contactRequests;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guest_id")
    public Long getGuestId() {
        return guestId;
    }

    @Column(name = "guest_name")
    public String getFirstName() {
        return firstName;
    }

    Guest() {

    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "guest")
//    @OneToMany(cascade=CascadeType.ALL, mappedBy="guest")
    @JsonManagedReference
    public Set<ContactRequest> getContactRequests() {
        return contactRequests;
    }

    public void setContactRequests(Set<ContactRequest> contactRequests) {
        this.contactRequests = contactRequests;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "guests")
    @JsonBackReference
    public Set<MyEvent> getMyEvents() {
        return myEvents;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMyEvents(Set<MyEvent> myEvents) {
        this.myEvents = myEvents;
    }

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    String company;

    @Column(nullable = false)
    String position;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(nullable = true)
    @Type(type = "text")
    @Lob
    private String image;

    @Column
    boolean showImage;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isShowImage() {
        return showImage;
    }

    public void setShowImage(boolean showImage) {
        this.showImage = showImage;
    }

    public Guest(String firstName, String lastName, String company, String position, String email, String password, String image, boolean showImage) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.myEvents = myEvents;
        this.lastName = lastName;
        this.company = company;
        this.position = position;
        this.email = email;
        this.password = password;
        this.image = image;
        this.showImage = showImage;

        myEvents = new HashSet<>();
        myEvents = null;
    }
}
