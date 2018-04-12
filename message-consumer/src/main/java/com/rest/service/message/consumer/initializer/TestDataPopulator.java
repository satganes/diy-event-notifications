package com.rest.service.message.consumer.initializer;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rest.service.message.consumer.model.TradeMessage;
import com.rest.service.message.consumer.repositories.TradeMessageRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Populate initial test data - sample
 *
 */
@Component
@Slf4j
public class TestDataPopulator implements CommandLineRunner
{

    private final TradeMessageRepository tradeMessage;

    public TestDataPopulator(TradeMessageRepository tradeMessage)
    {
        this.tradeMessage = tradeMessage;
    }

    @Override
    public void run(String[] args)
    {
        log.info("Populating initial data...");
        this.tradeMessage.deleteAll()
                .thenMany(Flux.just(100.00, 200.00)
                        .flatMap(amt -> this.tradeMessage.save(TradeMessage.builder()
                                .currencyFrom("EUR")
                                .currencyTo("GBP")
                                .amountSell(amt)
                                .amountBuy(50.00)
                                .rate(0.74)
                                .timePlaced(LocalDateTime.now().toString())
                                .originatingCountry("FR")
                                .build())))
                .log()
                .subscribe(null, null, () -> log.info("initialization is complete...!!!"));

    }

}