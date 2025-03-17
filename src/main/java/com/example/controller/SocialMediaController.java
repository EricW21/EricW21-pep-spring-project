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
        return ResponseEntity.status(HttpStatus.OK).body(created);

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
    @PostMapping("/messages")
    public ResponseEntity createMessage(@RequestBody Message message) {
        Message created = messageService.createMessage(message);
        if (created==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }
    @GetMapping("/messages")
    public ResponseEntity getAllMessages() {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessages());
    }
    @GetMapping("/messages/{message_id}")
    public ResponseEntity getMessageById(@PathVariable("message_id") Long messageId) {
        
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageById(messageId));
    }
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity deleteMessageById(@PathVariable("message_id") int messageId) {
        Message message = messageService.deleteMessageById(Integer.valueOf(messageId));
        if (message!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity updateMessageById(@PathVariable("message_id") int messageId, @RequestBody Message halfMessage) {
        String text = halfMessage.getMessageText();
        if (text.trim().isEmpty() || text.length() > 255) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Message message = messageService.updateMessageById(Integer.valueOf(messageId),text);
        if (message==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(1);
           
        }
    }
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity retrieveMessagesByPostedBy(@PathVariable("account_id") Integer postedBy) {
        return ResponseEntity.status(HttpStatus.OK).body(this.messageService.retrieveMessageByUser(postedBy));
    }

}
