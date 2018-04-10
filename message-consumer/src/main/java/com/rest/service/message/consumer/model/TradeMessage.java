package com.rest.service.message.consumer.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class TradeMessage
{
    // Using lombok
    @CreatedDate
    private LocalDateTime createdDate;

    @Id
    private String userId;
    @NotBlank
    private String currencyFrom;
    @NotBlank
    private String currencyTo;
    @NotBlank
    private Double amountSell;
    @NotBlank
    private Double amountBuy;
    @NotBlank
    private Double rate;
    @NotBlank
    private LocalDateTime timePlaced;
    @NotBlank
    private String originatingCountry;
}
