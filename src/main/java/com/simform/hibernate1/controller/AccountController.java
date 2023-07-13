package com.simform.hibernate1.controller;

import com.simform.hibernate1.entity.*;
import com.simform.hibernate1.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        Account savedAccount = accountService.create(account);
        return ResponseEntity.ok(savedAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> findAccountDetails() {
        List<Account> accounts = accountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("{num}")
    public ResponseEntity<Account> findByAccountNumber(@PathVariable("num") String accountNumber) {
        Account accountDetailsByAccountNumber = accountService.findAccountDetailsByAccountNumber(accountNumber);
        return new ResponseEntity<>(accountDetailsByAccountNumber, HttpStatus.FOUND);

    }

    @DeleteMapping("{num}")
    public ResponseEntity<String> deleteByAccountNumber(@PathVariable("num") String accountNumber) {
        accountService.deleteByAccountNumber(accountNumber);
        return new ResponseEntity<>("Deleted account", HttpStatus.NO_CONTENT);
    }

    @PutMapping("{num}")
    public ResponseEntity<Account> updateAccountDetails(@PathVariable("num") String accountNumber, @RequestBody Account account) {
        Account updatedAccount = accountService.updateByAccountNumber(accountNumber, account);
        return ResponseEntity.ok(updatedAccount);
    }

}
