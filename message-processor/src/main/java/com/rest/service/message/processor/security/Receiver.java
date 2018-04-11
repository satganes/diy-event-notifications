package com.rest.service.message.processor.security;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RabbitListener(queues = "tradeMessages")
@Slf4j
public class Receiver
{
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private SimpMessagingTemplate webSocket;

    @RabbitHandler
    public void receive(String input)
    {
        TradeMessage msg;
        try
        {
            msg = mapper.readValue(input, TradeMessage.class);
            webSocket.convertAndSend("/topic/greetings", mapper.writeValueAsString(msg.getAmountBuy()));
        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }

        log.info("[x] Received '" + input + "'");
    }
}
