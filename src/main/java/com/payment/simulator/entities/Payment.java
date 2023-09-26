package com.payment.simulator.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    private String paymentType;
    private BigDecimal value;

    private Long cardId;

    public Payment(){}

    public Payment(Long id, BigDecimal value, String paymentType, LocalDateTime dateTime, Long cardId) {
        this.id = id;
        this.value = value;
        this.paymentType = paymentType;
        this.dateTime = dateTime;
        this.cardId = cardId;
    }

    public Payment(BigDecimal value, String paymentType, LocalDateTime dateTime, Long cardId) {
        this.value = value;
        this.paymentType = paymentType;
        this.dateTime = dateTime;
        this.cardId = cardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long card) {
        this.cardId = card;
    }
}
