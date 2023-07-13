package com.simform.hibernate1.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "payment_info")
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    private String accountNo;
    private Double amount;
    private String cardType;

    public PaymentInfo(String accountNo, Double amount, String cardType) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.cardType = cardType;
    }
}
