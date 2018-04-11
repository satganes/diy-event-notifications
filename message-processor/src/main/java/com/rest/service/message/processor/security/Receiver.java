package com.rest.service.message.processor.security;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RabbitListener(queues = "tradeMessages")
@Slf4j
public class Receiver
{
ObjectMapper mapper = new ObjectMapper();
TradeMessage msg ;
    @RabbitHandler
    public void receive(String input)
    {
        try
        {
            msg = mapper.readValue(input, TradeMessage.class);
            log.error("Json : " + mapper.writeValueAsString(msg));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
        log.error("[x] Received '" + input + "'");
        
    }

}
