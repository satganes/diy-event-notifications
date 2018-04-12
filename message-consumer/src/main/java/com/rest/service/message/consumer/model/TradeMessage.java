package com.rest.service.message.consumer.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
public class TradeMessage implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private String userId;
    @NotBlank
    private String currencyFrom;
    @NotBlank
    private String currencyTo;
    @NotNull
    private Double amountSell;
    @NotNull
    private Double amountBuy;
    @NotNull
    private Double rate;
    private String timePlaced;
    @NotBlank
    private String originatingCountry;
}
