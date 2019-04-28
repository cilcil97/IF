package com.licitacija.mk;

import com.licitacija.mk.chatServer.chat.ChatServer;
import com.licitacija.mk.chatServer.chat.ChatServerRepository;
import com.licitacija.mk.mongoModels.OfferMongoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ServletComponentScan
@EnableMongoRepositories(basePackageClasses = MkApplication.class)
public class MkApplication implements CommandLineRunner {


    private final static Logger logger = LogManager.getLogger(MkApplication.class);

    private final OfferMongoRepository repository;
    private final ChatServerRepository chatServerRepository;

    public MkApplication(OfferMongoRepository repository, ChatServerRepository chatServerRepository) {
        this.repository = repository;
        this.chatServerRepository = chatServerRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(MkApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ChatServer server1=new ChatServer(9000, repository);
        server1=chatServerRepository.save(server1);
        server1.start();
        ChatServer server2=new ChatServer(9000, repository);
        server2=chatServerRepository.save(server2);
        server2.start();

    }
}
