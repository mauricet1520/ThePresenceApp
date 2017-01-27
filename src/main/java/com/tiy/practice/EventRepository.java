package com.tiy.practice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by crci1 on 1/27/2017.
 */
public interface EventRepository extends CrudRepository<Event, Integer> {

    List<Event> findByName(String name);
    List<Event> findByLocation(String location);
    List<Event> findByAddress(String address);

}
