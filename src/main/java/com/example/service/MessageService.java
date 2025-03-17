package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import java.sql.Timestamp;
import java.util.List;


import com.example.entity.Message;
import java.util.Optional;
import com.example.repository.MessageRepository;
@Service
@Transactional
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
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
}
