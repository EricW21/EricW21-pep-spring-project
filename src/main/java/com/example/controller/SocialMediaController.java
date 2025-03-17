package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Account;
import com.example.entity.Message;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
@RequestMapping("/")
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

   
    
    @PostMapping("/register")
    public ResponseEntity createAccount(@RequestBody Account account) {
        
        Account created = accountService.createAccount(account);
        if (created==null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(account);

    }

    @PostMapping("/login")
    public ResponseEntity processLogin(@RequestBody Account account) {
        Account accountOptional = accountService.processLogin(account);

        if (accountOptional!=null) {
           
            return ResponseEntity.status( HttpStatus.OK).body(accountOptional);
        } 
        else {
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(null); 
        }
    }

    @GetMapping("/messages")
    public ResponseEntity getAllMessages() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/messages/{message_id}")
    public ResponseEntity getMessageById(@PathVariable("message_id") Long messageId) {
        
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageById(messageId));
    }

}
