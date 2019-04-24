package com.citi.bridge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.citi.bridge.models.SSFiles;

@Repository
public interface SSFilesrepository extends MongoRepository<SSFiles, String>{


}
