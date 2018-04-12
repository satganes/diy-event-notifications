package com.rest.service.message.processor.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
public class TradeMessage
{

    private String userId;
    private String currencyFrom;
    private String currencyTo;
    private Double amountSell;
    private Double amountBuy;
    private Double rate;
    private String timePlaced;
    private String originatingCountry;
}
