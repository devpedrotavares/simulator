package com.payment.simulator.repositories;

import com.payment.simulator.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long>{

	
}
