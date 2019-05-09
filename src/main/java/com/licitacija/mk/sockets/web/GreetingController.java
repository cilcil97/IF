package com.licitacija.mk.sockets.web;

import com.licitacija.mk.sockets.mongoModel.OfferMongo;
import com.licitacija.mk.sockets.mongoRepository.OfferMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static java.lang.String.format;

@Controller
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final OfferMongoRepository repository;

    public GreetingController(SimpMessagingTemplate simpMessagingTemplate, OfferMongoRepository repository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.repository = repository;
    }

    @MessageMapping("{greetings}")
    public void greet(@DestinationVariable String greetings, String destination) {
        logger.info("Greeting for {}", destination);
        OfferMongo offerMongo = new OfferMongo();
        offerMongo.setBody(destination);
        offerMongo.setWhichProduct(greetings);
        logger.info("Saved in repository  for {}", offerMongo.getWhichProduct());
        repository.save(offerMongo);
        this.simpMessagingTemplate.convertAndSend(format("/topic/%s", greetings), destination);
    }


    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload String chatMessage) {

        simpMessagingTemplate.convertAndSend(format("/topic/%s", roomId), chatMessage);
    }

}
