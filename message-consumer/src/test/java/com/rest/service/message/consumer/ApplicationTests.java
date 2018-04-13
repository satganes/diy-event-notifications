package com.rest.service.message.consumer;

import java.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests
{
    @Autowired
    ApplicationContext context;

    WebTestClient client;

    @Before
    public void setup()
    {
        client = WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .baseUrl("http://localhost:8080/")
                .build();
    }

    @Test
    public void getAllPostsShouldBeOkWithAuthetication()
    {
        client.get()
                .uri("/tradeMessages/")
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    public void userDefinedMappingsAccessibleOnLogin()
    {
        client.get()
                .uri("/tradeMessages/")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "basic " + getBasicAuth())
                .exchange()
                .expectStatus()
                .isOk();
    }

    private String getBasicAuth()
    {
        return new String(Base64.getEncoder()
                .encode(("rob:rob").getBytes()));
    }

}
