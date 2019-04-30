package com.licitacija.mk.multipleChatRooms.mongoRepository;

import com.licitacija.mk.multipleChatRooms.mongoModel.OfferMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferMongoRepository extends MongoRepository<OfferMongo, String> {


}
