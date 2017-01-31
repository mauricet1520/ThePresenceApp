package com.tiy.practice;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by crci1 on 1/29/2017.
 */
@Entity
public class ContactRequest {
    private Long myRequestId;
    private String requesteeEmailAddress;
    private String requesterEmailAddress;
    private String requestStatus;
    private java.sql.Timestamp time;
    private Guest guest;

    @ManyToOne(cascade = CascadeType.MERGE)
//    @ManyToOne // <--- note that you have properties specified here - keep your own
    @JsonBackReference
    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")

    public Long getMyRequestId() {
        return myRequestId;
    }

    public void setMyRequestId(Long myRequestId) {
        this.myRequestId = myRequestId;
    }

    @Column(name = "requestee_email_address")
    public String getRequesteeEmailAddress() {
        return requesteeEmailAddress;
    }

    public void setRequesteeEmailAddress(String requesteeEmailAddress) {
        this.requesteeEmailAddress = requesteeEmailAddress;
    }

    @Column(name = "requester_email_address")
    public String getRequesterEmailAddress() {
        return requesterEmailAddress;
    }

    public void setRequesterEmailAddress(String requesterEmailAddress) {
        this.requesterEmailAddress = requesterEmailAddress;
    }

    @Column(name = "request_status")
    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Timestamp getTime() {
        return time;
    }

    @Column(name = "request_time")
    public void setTime(Timestamp time) {
        this.time = time;
    }

    public ContactRequest(String requesteeEmailAddress, String requesterEmailAddress, String requestStatus, Timestamp time) {
        this.requesteeEmailAddress = requesteeEmailAddress;
        this.requesterEmailAddress = requesterEmailAddress;
        this.requestStatus = requestStatus;
        this.time = time;
    }

    public ContactRequest() {
    }
}
