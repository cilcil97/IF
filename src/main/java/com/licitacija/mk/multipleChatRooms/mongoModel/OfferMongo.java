package com.licitacija.mk.multipleChatRooms.mongoModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OfferMongo {

    @Id
    private String id;

    private String body;

    public OfferMongo() {
    }

    public OfferMongo(String id, String body) {
        this.id = id;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
