package com.tiy.practice;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by crci1 on 1/29/2017.
 */
public interface RequestRepository extends CrudRepository<ContactRequest, Long> {
    ContactRequest findByRequesteeEmailAddress(String requesteeEmailAddress);
    ContactRequest findByRequesterEmailAddress(String requesterEmailAddress);
}
