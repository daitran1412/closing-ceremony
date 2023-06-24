package com.hus.ceremony.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hus.ceremony.Entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>  {
    
    List<Message> findByName(String name);

    List<Message> findByDateTime(String dateTime);

    Message findByMessageId(int messageId);

    List<Message> findAll();

    void deleteByMessageId(int messageId);

    void deleteAll();

}
