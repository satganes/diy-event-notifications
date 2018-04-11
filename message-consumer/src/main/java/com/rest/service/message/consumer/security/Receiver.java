package com.rest.service.message.consumer.security;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import lombok.extern.slf4j.Slf4j;

@RabbitListener(queues = "tradeMessages")
@Slf4j
public class Receiver
{

    @RabbitHandler
    public void receive(String in)
    {
        log.error("[x] Received '" + in + "'");
    }

}
