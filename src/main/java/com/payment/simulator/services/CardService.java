package com.payment.simulator.services;

import com.payment.simulator.entities.Card;
import com.payment.simulator.repositories.CardRepository;
import com.payment.simulator.services.exceptions.InvalidCardException;
import com.payment.simulator.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PaymentService paymentService;

    public List<Card> getCards() {
        List<Card> cards = cardRepository.findAll();

        for(Card card : cards) {
            card.setPayments(paymentService.getPaymentsByCardId(card.getId()));
        }

        return cards;
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow();
    }

    public Card addCard(Card card) {
        _validateCard(card);

        return cardRepository.save(card);
    }

    public Card updateCardById(Long id, Card card) {
        Card existingCard = cardRepository.findById(id).orElseThrow();

        _validateCard(card);

        existingCard.setBalance(card.getBalance());
        existingCard.setName(card.getName());
        existingCard.setNumber(card.getNumber());
        existingCard.setInvoice(card.getInvoice());
        existingCard.setLimit(card.getLimit());
        existingCard.setSecurityCode(card.getSecurityCode());

        return cardRepository.save(existingCard);
    }

    private void _validateCard(Card card) {
        // Number

        if(card.getNumber().length() != 16) {
            throw new InvalidCardException("Número inválido: o número deve ter 16 digitos");
        }

        // Expiration date

        LocalDate expirationDate = card.getExpirationDate();

        if (expirationDate.isBefore(LocalDate.now().minusDays(1))) {
            throw new InvalidCardException("Este cartão já expirou!");
        }

        // Security Code

        String securityCode = card.getSecurityCode();

        if (securityCode.length() != 3) {
            throw new InvalidCardException("Código de segurança inválido: deve ter 3 digitos");
        }
    }

    public void deleteCardById(Long cardId) {
        if (!cardRepository.existsById(cardId)) {
            throw new ResourceNotFoundException("Cartão não encontrado");
        }

        cardRepository.deleteById(cardId);
    }
}
