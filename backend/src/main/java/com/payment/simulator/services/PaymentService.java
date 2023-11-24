package com.payment.simulator.services;

import com.payment.simulator.entities.Card;
import com.payment.simulator.entities.Payment;
import com.payment.simulator.entities.constants.PaymentTypeConstants;
import com.payment.simulator.repositories.CardRepository;
import com.payment.simulator.repositories.PaymentRepository;
import com.payment.simulator.services.exceptions.InvalidCardException;
import com.payment.simulator.services.exceptions.InvalidPaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByCardId(Long id) {
        return paymentRepository.findByCardId(id);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow();
    }

    public Payment addPayment(Payment payment) {

        // validate and process payment

        String paymentType = payment.getPaymentType();

        if(!paymentType.equals(PaymentTypeConstants.CREDIT) &&
                !paymentType.equals(PaymentTypeConstants.DEBIT)) {

            throw new InvalidPaymentException("Tipo de pagamento inválido: deve ser crédito ou débito");
        }

        Card card = cardRepository.findById(payment.getCardId())
                .orElseThrow(() -> new InvalidPaymentException("Cartão não existente"));

        LocalDate expirationDate = card.getExpirationDate();

        if (expirationDate.isBefore(LocalDate.now().minusDays(1))) {
            throw new InvalidCardException("Este cartão já expirou!");
        }

        BigDecimal value = payment.getValue();

        if(paymentType.equals(PaymentTypeConstants.DEBIT)) {
            BigDecimal balance = card.getBalance();

            if(balance.compareTo(value) < 0) {
                throw new InvalidPaymentException("Não é possível realizar débito, pois não há saldo disponível");
            }

            BigDecimal newBalance = balance.subtract(value);

            card.setBalance(newBalance);
        }
        else {
            BigDecimal invoice = card.getInvoice();

            if(invoice.add(value).compareTo(card.getLimit()) > 0) {
                throw new InvalidPaymentException("Limite estourado");
            }

            BigDecimal newInvoice = invoice.add(value);

            card.setInvoice(newInvoice);
        }

        cardRepository.save(card);

        // save payment

        payment.setDateTime(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

}
