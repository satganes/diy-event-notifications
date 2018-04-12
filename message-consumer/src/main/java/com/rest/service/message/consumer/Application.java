package com.rest.service.message.consumer;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rest.service.message.consumer.handlers.TradeMessageHandler;
import com.rest.service.message.consumer.listeners.TradeMessageListener;

@SpringBootApplication
@EnableMongoAuditing
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public TradeMessageListener tradeMessageListener()
    {
        return new TradeMessageListener();
    }

//    @Bean
//    public RouterFunction<ServerResponse> routes(TradeMessageHandler handler)
//    {
//        return route(GET("/tradeMessages"), handler::all).andRoute(POST("/tradeMessages"), handler::create)
//                .andRoute(GET("/tradeMessages/{id}"), handler::get)
//                .andRoute(PUT("/tradeMessages/{id}"), handler::update)
//                .andRoute(DELETE("/tradeMessages/{id}"), handler::delete);
//    }
}
