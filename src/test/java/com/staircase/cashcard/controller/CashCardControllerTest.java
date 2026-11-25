package com.staircase.cashcard.controller;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.staircase.cashcard.entity.CashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.client.EntityExchangeResult;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class CashCardControllerTest {

    @Autowired
    RestTestClient restTestClient;

    @Test
    @DirtiesContext
    void createCashCard(){
        CashCard cashCard=new CashCard(null,2424.24);
        EntityExchangeResult<CashCard> response=restTestClient.post().uri("/cashcards")
                .body(cashCard)
                .exchange()
                .returnResult(CashCard.class);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewCashCard=response.getResponseHeaders().getLocation();
        EntityExchangeResult<CashCard> createdResponse= null;
        if (locationOfNewCashCard != null) {
            createdResponse = restTestClient.get().uri(locationOfNewCashCard.getPath()) .exchange()
                    .returnResult(CashCard.class);
        }
        assertThat(createdResponse.getStatus()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getCashCard(){
        EntityExchangeResult<CashCard> response=restTestClient.get().uri("/cashcards/2")
                .exchange()
                .returnResult(CashCard.class);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        CashCard cashCard= response.getResponseBody();
        assertThat(Objects.requireNonNull(cashCard).id()).isNotNull();
    }


    @Test
    void getAllCashCards(){
        int size=1;
        EntityExchangeResult<List<CashCard>> response=restTestClient.get().uri("/cashcards?page=0&size="+size)
                .exchange()
                .returnResult(new ParameterizedTypeReference<List<CashCard>>() {});
        List<CashCard> cashCards= response.getResponseBody();
        assertThat(cashCards.size()).isEqualTo(size);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);

    }
}
