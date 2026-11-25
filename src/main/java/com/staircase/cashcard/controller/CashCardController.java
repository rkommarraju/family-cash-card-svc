package com.staircase.cashcard.controller;


import com.staircase.cashcard.entity.CashCard;
import com.staircase.cashcard.repository.CashCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    @Autowired
    CashCardRepository cashCardRepository;

    @PostMapping
    ResponseEntity<CashCard> createCashCard(@RequestBody CashCard cashCard,UriComponentsBuilder uriComponentsBuilder){

        CashCard response=cashCardRepository.save(cashCard);
        URI location=uriComponentsBuilder.path("/cashcards/{cardId}").buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }
    @GetMapping("/{cardId}")
    ResponseEntity<CashCard> getCashCard(@PathVariable Long cardId){

        Optional<CashCard> cashCardOptional=cashCardRepository.findById(cardId);
        if(cashCardOptional.isPresent()){
            return  ResponseEntity.ok(cashCardOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
