package com.simform.hibernate1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts = new ArrayList<>();
    public void add(Account account) {
        this.accounts.add(account);
    }
}
