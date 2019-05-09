package com.licitacija.mk.sockets.mongoRepository;

import com.licitacija.mk.sockets.mongoModel.OfferMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferMongoRepository extends MongoRepository<OfferMongo, String> {


}
