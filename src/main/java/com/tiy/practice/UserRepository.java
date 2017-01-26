package com.tiy.practice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by crci1 on 1/26/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    List<User> findByCompany(String company);
    List<User> findByPosition(String position);
    List<User> findByEmail(String email);
    List<User> findByPassword(String password);
}
