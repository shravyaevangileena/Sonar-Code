package com.creditcard.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditcard.entities.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
	CreditCard findByCardId(long cardId);

}