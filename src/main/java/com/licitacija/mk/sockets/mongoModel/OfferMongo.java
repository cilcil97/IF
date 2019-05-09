package com.licitacija.mk.sockets.mongoModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OfferMongo {

    @Id
    private String id;

    private String body;

    private String whichProduct;


    public String getWhichProduct() {
        return whichProduct;
    }

    public void setWhichProduct(String whichProduct) {
        this.whichProduct = whichProduct;
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
