package com.rest.service.message.processor.handler;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.service.message.processor.model.TradeMessage;

import lombok.extern.slf4j.Slf4j;

@RabbitListener(queues = "tradeMessages")
@Slf4j
public class RabbitMgEventHandler
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
            // Process and aggregate the message attributes here inorder to populate a pictorial representation
            // of some metrics in the UI
            webSocket.convertAndSend("/topic/messages", msg);
        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }

        log.info("[x] Received '" + input + "'");
    }
}
