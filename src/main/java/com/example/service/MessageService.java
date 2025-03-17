package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import com.example.entity.Account;
import com.example.entity.Message;
import java.util.Optional;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
@Service
@Transactional
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message){
        if (message.getMessageText().length()==0 || message.getMessageText().length()>255) {
            return null;
        }
        Optional<Account> account = accountRepository.findAccountByAccountId(message.getPostedBy());
        if (!account.isPresent()) {
            return null;
        }
        Optional<Message> messageOptional = messageRepository.findMessageByPostedByAndMessageTextAndTimePostedEpoch(message.getPostedBy(),message.getMessageText(),message.getTimePostedEpoch());

        if (messageOptional.isPresent()) {
            
            return null;
        }
        return messageRepository.save(message);
    }
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(long id) {
        
        Optional<Message> message = messageRepository.findMessageByMessageId(Integer.valueOf((int)id));
        if (message.isPresent()) {
            return message.get();
        }
        return null;
    }
    public Message deleteMessageById(Integer id) {
        Message message = this.getMessageById(id);
        if (message==null) {
            return null;
        }
        this.messageRepository.delete(message);
        return message;
    }

    public Message updateMessageById(Integer id,String text) {
        Message message = this.getMessageById(Integer.valueOf(id.intValue()));
        if (message==null) {
            return null;
        }
        if (text.trim().length()==0 || text.length()>255) {
            return null;
        }
        message.setMessageText(text);
        return this.messageRepository.save(message);
       
    }

    public List<Message> retrieveMessageByUser(Integer userId) {
        return this.messageRepository.findByPostedBy(userId);
    }

}
