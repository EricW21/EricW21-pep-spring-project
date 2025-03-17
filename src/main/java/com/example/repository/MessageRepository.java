package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;


import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

    // Message findMessageById(long id);

    // Message createMessage(Message Message);

    // Message deleteMessageById(int id);

    // Message updateMessageText(int id, String message_text);

    // List<Message> retrieveMessagesByUserId(int user_id);

    // List<Message> getAllMessages();
}
