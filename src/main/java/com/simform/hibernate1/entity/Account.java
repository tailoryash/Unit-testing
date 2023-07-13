package com.simform.hibernate1.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.*;

@Entity
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
