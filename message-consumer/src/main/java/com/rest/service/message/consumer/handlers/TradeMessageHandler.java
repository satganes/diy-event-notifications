package com.rest.service.message.consumer.handlers;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rest.service.message.consumer.model.TradeMessage;
import com.rest.service.message.consumer.repositories.TradeMessageRepository;

import reactor.core.publisher.Mono;

@Component
public class TradeMessageHandler
{

    private final TradeMessageRepository messages;

    public TradeMessageHandler(TradeMessageRepository tradeMessages)
    {
        this.messages = tradeMessages;
    }

    public Mono<ServerResponse> all(ServerRequest req)
    {
        return ServerResponse.ok()
                .body(this.messages.findAll(), TradeMessage.class);
    }

    public Mono<ServerResponse> create(ServerRequest request)
    {
        return request.bodyToMono(TradeMessage.class)
                .flatMap(tradeMessage -> this.messages.save(tradeMessage))
                .flatMap(msg -> ServerResponse.created(URI.create("/tradeMessages/" + msg.getUserId()))
                        .build());
    }

    public Mono<ServerResponse> get(ServerRequest req)
    {
        return this.messages.findById(req.pathVariable("id"))
                .flatMap(tradeMessage -> ServerResponse.ok()
                        .body(Mono.just(tradeMessage), TradeMessage.class))
                .switchIfEmpty(ServerResponse.notFound()
                        .build());
    }

    public Mono<ServerResponse> update(ServerRequest request)
    {
        return Mono.zip((data) ->
        {
            TradeMessage msg = (TradeMessage) data[0];
            TradeMessage tradeMessage = (TradeMessage) data[0];

            msg.setCurrencyFrom(tradeMessage.getCurrencyFrom());
            msg.setCurrencyTo(tradeMessage.getCurrencyTo());
            msg.setAmountSell(tradeMessage.getAmountSell());
            msg.setAmountBuy(tradeMessage.getAmountBuy());
            msg.setRate(tradeMessage.getRate());
            msg.setOriginatingCountry(tradeMessage.getOriginatingCountry());
            msg.setTimePlaced(tradeMessage.getTimePlaced());

            return msg;

        }, this.messages.findById(request.pathVariable("id")), request.bodyToMono(TradeMessage.class))
                .cast(TradeMessage.class)
                .flatMap(tradeMessage -> this.messages.save(tradeMessage))
                .flatMap(tradeMessage -> ServerResponse.noContent()
                        .build());
    }

    public Mono<ServerResponse> delete(ServerRequest req)
    {
        return ServerResponse.noContent()
                .build(this.messages.deleteById(req.pathVariable("id")));
    }

}