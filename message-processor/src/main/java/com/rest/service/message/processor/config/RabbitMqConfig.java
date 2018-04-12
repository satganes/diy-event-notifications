package com.rest.service.message.processor.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rest.service.message.processor.handler.RabbitMgEventHandler;

@Configuration
public class RabbitMqConfig
{
    @Bean
    public Queue hello()
    {
        return new Queue("tradeMessages");
    }

    @Bean
    public RabbitMgEventHandler receiver()
    {
        return new RabbitMgEventHandler();
    }

}
