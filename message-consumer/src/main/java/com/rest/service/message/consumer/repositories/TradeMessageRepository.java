package com.rest.service.message.consumer.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rest.service.message.consumer.model.TradeMessage;

/**
 * Reactive Mongo Repository
 *
 */
public interface TradeMessageRepository extends ReactiveMongoRepository<TradeMessage, String>
{
}