package com.staircase.cashcard.controller;


import com.staircase.cashcard.entity.CashCard;
import com.staircase.cashcard.repository.CashCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    @Autowired
    CashCardRepository cashCardRepository;

    @GetMapping("/{cardId}")
    public ResponseEntity<CashCard> getCashCard(@PathVariable Long cardId){

        Optional<CashCard> cashCardOptional=cashCardRepository.findById(cardId);
        if(cashCardOptional.isPresent()){
            return  ResponseEntity.ok(cashCardOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
