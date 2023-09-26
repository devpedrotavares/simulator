package com.payment.simulator.repositories;

import com.payment.simulator.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long>{


    List<Payment> findByCardId(Long id);
}
