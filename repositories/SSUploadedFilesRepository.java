package com.citi.bridge.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.citi.bridge.models.UploadedFiles;

@Repository
public interface SSUploadedFilesRepository extends MongoRepository<UploadedFiles, String> {

}


