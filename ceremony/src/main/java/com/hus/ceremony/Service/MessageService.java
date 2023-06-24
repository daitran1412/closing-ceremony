package com.hus.ceremony.Service;

import java.util.List;

import com.hus.ceremony.Dto.MessageDto;
import com.hus.ceremony.Entity.Message;

public interface MessageService {
        
        Message createMessage(MessageDto messageDto);

        Message getMessageById(int messageId);

        List<Message> getMessageByNameList(String name);

        List<Message> getMessageByDateTimeList(String dateTime);

        List<Message> getAllMessage();

        void deleteMessageById(int messageId);

        void deleteAllMessage();

        void deleteListMessage(List<Integer> messageId);

}
