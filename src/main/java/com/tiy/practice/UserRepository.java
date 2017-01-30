package com.tiy.practice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by crci1 on 1/26/2017.
 */
public interface UserRepository extends CrudRepository<Guest, Long> {
    Guest findByFirstName(String firstName);
    List<Guest> findByLastName(String lastName);
    List<Guest> findByCompany(String company);
    List<Guest> findByPosition(String position);
    Guest findByEmail(String email);
    List<Guest> findByPassword(String password);
}
