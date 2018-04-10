package com.rest.service.message.consumer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TradeMessageNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public TradeMessageNotFoundException(String id)
    {
        super("Trade Message with id: " + id + " is not found");
    }

}
