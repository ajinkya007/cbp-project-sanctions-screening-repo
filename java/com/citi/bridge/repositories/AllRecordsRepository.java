package com.citi.bridge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.citi.bridge.models.AllRecords;

@Repository
public interface AllRecordsRepository extends MongoRepository<AllRecords, String>{

}
