package com.staircase.cashcard.repository;


import com.staircase.cashcard.entity.CashCard;
import org.springframework.data.repository.CrudRepository;

public interface CashCardRepository extends CrudRepository<CashCard, Long> {
}
