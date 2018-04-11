package com.rest.service.message.consumer.security;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig
{
    @Bean
    public Queue hello()
    {
        return new Queue("tradeMessages");
    }

    @Bean
    public Receiver receiver()
    {
        return new Receiver();
    }

}
