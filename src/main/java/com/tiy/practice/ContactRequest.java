package com.tiy.practice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by crci1 on 1/29/2017.
 */
@Entity
public class ContactRequest {
    private Long myRequestId;
    private String toUser;
    private String fromUser;
    private String requestStatus;
    private java.sql.Timestamp time;
    private Guest guest;


    @ManyToOne // <--- note that you have properties specified here - keep your own
    @JsonBackReference
    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="request_id")

    public Long getMyRequestId() {
        return myRequestId;
    }

    public void setMyRequestId(Long myRequestId) {
        this.myRequestId = myRequestId;
    }


    @Column(name="request_name")
    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @Column(name="request_user")
    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @Column(name="request_status")
    public String getRequestStatus() {
        return requestStatus;
    }


    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Timestamp getTime() {
        return time;
    }

    @Column(name="request_time")
    public void setTime(Timestamp time) {
        this.time = time;
    }

    public ContactRequest(String toUser, String fromUser, String requestStatus, Timestamp time) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.requestStatus = requestStatus;
        this.time = time;
    }

    public ContactRequest() {
    }
}
