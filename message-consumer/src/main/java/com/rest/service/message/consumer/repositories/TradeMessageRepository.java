package com.rest.service.message.consumer.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rest.service.message.consumer.model.TradeMessage;

public interface TradeMessageRepository extends ReactiveMongoRepository<TradeMessage, String>
{
}