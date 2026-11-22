package com.staircase.cashcard.controller;


import com.staircase.cashcard.entity.CashCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    @GetMapping("/{cardId}")
    private ResponseEntity<CashCard> getCashCard(@PathVariable Long cardId){
        CashCard cashCard=new CashCard(2L,2323.23);
        return  ResponseEntity.ok(cashCard);
    }
}
