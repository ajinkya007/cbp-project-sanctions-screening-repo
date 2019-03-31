package com.example.SansactionScreeningBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.SansactionScreening.repositories.SSRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = SSRepository.class)
public class SansactionScreeningBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SansactionScreeningBackendApplication.class, args);
	}

}
