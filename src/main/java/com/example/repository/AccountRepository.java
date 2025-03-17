package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

    

    // Account createAccount(Account account);

    Optional<Account> findAccountByUsernameAndPassword(String username, String password);

    


}
