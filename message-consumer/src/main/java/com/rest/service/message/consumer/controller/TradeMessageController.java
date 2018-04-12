package com.rest.service.message.consumer.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.service.message.consumer.exceptions.TradeMessageNotFoundException;
import com.rest.service.message.consumer.model.TradeMessage;
import com.rest.service.message.consumer.repositories.TradeMessageRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(value = "/tradeMessages")
public class TradeMessageController
{

    private final TradeMessageRepository repository;

    public TradeMessageController(TradeMessageRepository tradeMessage)
    {
        this.repository = tradeMessage;
    }

    @GetMapping("")
    public Flux<TradeMessage> all()
    {

        return this.repository.findAll();
    }

    @PostMapping("")
    public Mono<TradeMessage> create(@RequestBody TradeMessage tradeMessage)
    {
        return this.repository.save(tradeMessage);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TradeMessage>> get(@PathVariable("id") String id)
    {
        return repository.findById(id)
                .map(msg -> ResponseEntity.ok(msg))
                .switchIfEmpty(Mono.error(new TradeMessageNotFoundException(id)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<TradeMessage>> update(@PathVariable("id") String id,
            @Valid @RequestBody TradeMessage tradeMessage)
    {
        return repository.findById(id)
                .flatMap(msg ->
                {
                    msg.setCurrencyFrom(tradeMessage.getCurrencyFrom());
                    msg.setCurrencyTo(tradeMessage.getCurrencyTo());
                    msg.setAmountSell(tradeMessage.getAmountSell());
                    msg.setAmountBuy(tradeMessage.getAmountBuy());
                    msg.setRate(tradeMessage.getRate());
                    msg.setOriginatingCountry(tradeMessage.getOriginatingCountry());
                    msg.setTimePlaced(tradeMessage.getTimePlaced());

                    return repository.save(msg);
                })
                .map(msg -> ResponseEntity.ok(msg))
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build());

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id)
    {
        return repository.findById(id)
                .flatMap(msg -> repository.delete(msg)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler({ TradeMessageNotFoundException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public static ResponseEntity<?> handleNotFoundException(TradeMessageNotFoundException exception)
    {
        return new ResponseEntity<String>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

}
