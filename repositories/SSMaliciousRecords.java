package com.citi.bridge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.citi.bridge.models.MRecords;

@Repository
public interface SSMaliciousRecords  extends MongoRepository<MRecords, String>{

}
