package com.rest.service.message.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.rest.service.message.consumer.listeners.TradeMessageListener;

@SpringBootApplication
@EnableMongoAuditing
public class Application
{
    /**
     * Main method to run the application locally
     */
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Registering the TradeMessage Listener bean here
     */
    @Bean
    public TradeMessageListener tradeMessageListener()
    {
        return new TradeMessageListener();
    }

    /*Another way to handle REST calls using Routers*/
    /*@Bean
    public RouterFunction<ServerResponse> routes(TradeMessageHandler handler)
    {
        return route(GET("/tradeMessages"), handler::all).andRoute(POST("/tradeMessages"), handler::create)
                .andRoute(GET("/tradeMessages/{id}"), handler::get)
                .andRoute(PUT("/tradeMessages/{id}"), handler::update)
                .andRoute(DELETE("/tradeMessages/{id}"), handler::delete);
    }*/
}
