package com.licitacija.mk;

import com.licitacija.mk.config.AppProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ServletComponentScan
@EnableMongoRepositories(basePackageClasses = MkApplication.class)
@EnableConfigurationProperties(AppProperties.class)
public class MkApplication {


    private final static Logger logger = LogManager.getLogger(MkApplication.class);


//    public MkApplication(OfferMongoRepository repository, ChatServerRepository chatServerRepository) {
//        this.repository = repository;
//        this.chatServerRepository = chatServerRepository;
//    }


    public static void main(String[] args) {
        SpringApplication.run(MkApplication.class, args);
    }

}
