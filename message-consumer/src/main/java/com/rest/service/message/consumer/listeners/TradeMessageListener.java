package com.rest.service.message.consumer.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;

import com.rest.service.message.consumer.model.TradeMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TradeMessageListener extends AbstractMongoEventListener<TradeMessage>
{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void onAfterSave(AfterSaveEvent<TradeMessage> event)
    {
        log.info("The received event : " + event.getSource()
                .getCurrencyFrom());
        log.info("The received document is : " + event.getCollectionName());
        super.onAfterSave(event);
    }

}
