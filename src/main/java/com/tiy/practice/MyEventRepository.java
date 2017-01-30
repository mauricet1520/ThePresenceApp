package com.tiy.practice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by crci1 on 1/27/2017.
 */
public interface MyEventRepository extends CrudRepository<MyEvent, Long> {

    MyEvent findByEventName(String eventName);
    List<MyEvent> findByLocation(String location);
    List<MyEvent> findByAddress(String address);

}
