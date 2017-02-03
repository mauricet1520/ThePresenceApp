package com.tiy.practice;

import javax.persistence.*;

/**
 * Created by crci1 on 2/1/2017.
 */

@Entity
@Table(name = "persons")
public class TestPerson {



    @Id
    @GeneratedValue
    Integer id;
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String profession;

    public TestPerson(String firstName, String lastName, String profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
    }

    public TestPerson() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
