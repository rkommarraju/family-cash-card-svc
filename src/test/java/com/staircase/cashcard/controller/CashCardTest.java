package com.staircase.cashcard.controller;


import com.staircase.cashcard.entity.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.client.EntityExchangeResult;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class CashCardTest {

    @Autowired
    RestTestClient restTestClient;

    @Test
    void getCashCard(){
        EntityExchangeResult<Card> response=restTestClient.get().uri("/cashcards/2") .exchange()
                .returnResult(Card.class);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
    }
}
