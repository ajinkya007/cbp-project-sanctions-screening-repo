package com.citi.bridge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.citi.bridge.models.SSInvalidFiles;

@Repository
public interface SSInvalidFilesRepository extends MongoRepository<SSInvalidFiles, String> {

}
