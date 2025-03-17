package com.example.service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;


import com.example.entity.Account;

import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import java.util.List;

@Service
@Transactional
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    public Account createAccount(Account account){
        Optional<Account> accountOptional = accountRepository.findAccountByUsernameAndPassword(account.getUsername(),account.getPassword());
        if (accountOptional.isPresent()) {
            
            return null;
        }
        return accountRepository.save(account);
    }
    public Account processLogin(Account account) {
        Optional<Account> accountOptional = accountRepository.findAccountByUsernameAndPassword(account.getUsername(),account.getPassword());
        if (accountOptional.isPresent()) {
            Account returned_account = accountOptional.get();
            return returned_account;
        }
        return null;
        
    }
}
