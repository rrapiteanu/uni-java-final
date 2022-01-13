package com.telemed.repository;

import com.telemed.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends  JpaRepository<Message, Integer> {
    Message findMessageById(Long id);
    List<Message> findMessagesByConversation(Conversation conversation);
}


