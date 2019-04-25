package com.citi.bridge.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.citi.bridge.models.SS;


/**
 * @author Lenovo
 *
 */
@Repository
public interface SSRepository extends MongoRepository<SS, String>{

}

