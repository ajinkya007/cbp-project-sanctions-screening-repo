package com.citi.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.citi.bridge.controller.FileUploadController;
import com.citi.bridge.repositories.*;
import com.citi.bridge.storage.StorageProperties;
import com.citi.bridge.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableMongoRepositories(basePackageClasses = {SSRepository.class})
public class Application {

    public static void main(String[] args) {
    	
    	final Logger logger = LoggerFactory.getLogger( Application.class);
    	logger.info("Application Started ... ");
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
