package com.hus.ceremony.Service.Implement;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hus.ceremony.Dto.MessageDto;
import com.hus.ceremony.Entity.Message;
import com.hus.ceremony.Repository.MessageRepository;
import com.hus.ceremony.Service.MessageService;

@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Message createMessage(MessageDto messageDto) {

        Message message = modelMapper.map(messageDto, Message.class);
        String dateTime = java.time.LocalDateTime.now().toString();
        message.setDateTime(dateTime);
        if (message.getName() == null) {
            message.setName("Anonymous");
        }
        return messageRepository.save(message);

    }

    @Override
    public Message getMessageById(int messageId) {

        return messageRepository.findByMessageId(messageId);

    }

    @Override
    public List<Message> getMessageByNameList(String name) {
        
        return messageRepository.findByName(name);

    }

    @Override
    public List<Message> getMessageByDateTimeList(String dateTime) {

        return messageRepository.findByDateTime(dateTime);

    }

    @Override
    public List<Message> getAllMessage() {

        return messageRepository.findAll();

    }
    
    @Override
    public void deleteMessageById(int messageId) {
        
        messageRepository.deleteById(messageId);

    }

    @Override
    public void deleteAllMessage() {

        messageRepository.deleteAll();

    }

    @Override
    public void deleteListMessage(List<Integer> messageId) {

        for (int id : messageId) {
            messageRepository.deleteById(id);
        }

    }
    
}
