package com.staircase.cashcard.controller;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.staircase.cashcard.entity.CashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.client.EntityExchangeResult;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class CashCardControllerTest {

    @Autowired
    RestTestClient restTestClient;

    @Test
    void getCashCard(){
        EntityExchangeResult<CashCard> response=restTestClient.get().uri("/cashcards/2") .exchange()
                .returnResult(CashCard.class);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        CashCard cashCard= response.getResponseBody();
        assertThat(Objects.requireNonNull(cashCard).id()).isNotNull();
    }
}
