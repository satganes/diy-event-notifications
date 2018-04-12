package com.rest.service.message.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig
{
    @Bean
    public Queue tradeMessages()
    {
        return new Queue("tradeMessages");
    }
}
