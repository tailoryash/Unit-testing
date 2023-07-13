package com.simform.hibernate1.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", unique = true)
    private String bankName;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        account.setBank(this);
        this.accounts.add(account);
    }
}