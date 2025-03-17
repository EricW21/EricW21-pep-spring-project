package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


import com.example.entity.Message;
import java.util.Optional;
public interface MessageRepository extends JpaRepository<Message, Long>{


    Optional<Message> findMessageByMessageId(Integer id);

    Optional<Message> findMessageByPostedByAndMessageTextAndTimePostedEpoch(Integer user_id,String test,Long timePostedEpoch);

    List<Message> findByPostedBy(Integer postedBy);
    
}
