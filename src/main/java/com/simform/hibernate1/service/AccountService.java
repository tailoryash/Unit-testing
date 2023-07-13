package com.simform.hibernate1.service;

import com.simform.hibernate1.entity.*;
import com.simform.hibernate1.exception.*;
import com.simform.hibernate1.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankRepository bankRepository;


    public Account create(Account account) {

        account.getCustomer().add(account);
        List<Bank> banks = bankRepository.findByBankName(account.getBank().getBankName());
        if (!banks.isEmpty()) {
            Bank bank = banks.get(0);
            bank.addAccount(account);
        } else {
            account.getBank().addAccount(account);
        }
        accountRepository.save(account);
        return account;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findAccountDetailsByAccountNumber(String accountNumber) {
        Account byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if(byAccountNumber != null){
            return byAccountNumber;
        }else{
            throw new UserNotFoundException();
        }
    }

    public void deleteByAccountNumber(String accountNumber) {
        Account byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if(byAccountNumber != null){
            accountRepository.delete(byAccountNumber);
        }else{
            throw new UserNotFoundException();
        }

    }

    public Account updateByAccountNumber(String accountNumber, Account account) {
        Account existingAccount = accountRepository.findByAccountNumber(accountNumber);
        if(existingAccount != null){
            existingAccount.getCustomer().add(account);
            if (existingAccount.getBank().getBankName() == account.getBank().getBankName()){
                existingAccount.getBank().addAccount(account);
            }else{
                bankRepository.save(account.getBank());
            }
            account.setId(existingAccount.getId());
            accountRepository.save(account);
            return account;
        }else{
            throw new UserNotFoundException();
        }
    }
}
