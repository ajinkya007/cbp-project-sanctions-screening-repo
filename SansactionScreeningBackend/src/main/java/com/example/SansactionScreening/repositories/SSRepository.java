/**
 * 
 */
package com.example.SansactionScreening.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SansactionScreening.models.SS;

/**
 * @author Lenovo
 *
 */
@Repository
public interface SSRepository extends MongoRepository<SS, String>{

}
