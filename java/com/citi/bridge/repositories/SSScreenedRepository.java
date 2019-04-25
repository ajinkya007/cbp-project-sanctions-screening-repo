package com.citi.bridge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.citi.bridge.models.ScreenedRecords;

@Repository
public interface SSScreenedRepository extends MongoRepository<ScreenedRecords, String>{

}
