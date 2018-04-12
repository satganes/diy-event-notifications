package com.rest.service.message.processor;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.service.message.processor.security.TradeMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TradeMessageListener extends AbstractMongoEventListener<TradeMessage>
{

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Autowired
    MongoTemplate mongoTemplate;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAfterSave(AfterSaveEvent<TradeMessage> event)
    {
        log.info("The received event : " + event.toString());
        log.info("Pushing into Rabbit MQ for broker");
        try
        {
            log.info("The received event : " + mapper.writeValueAsString(event.getSource()));
            this.template.convertAndSend(queue.getName(), mapper.writeValueAsString(event.getSource()));
        }
        catch (AmqpException | JsonProcessingException e)
        {
            log.error(e.getMessage());
        }
        super.onAfterSave(event);
    }

}
