package com.hus.ceremony.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.hus.ceremony.Dto.MessageDto;
import com.hus.ceremony.Entity.Message;
import com.hus.ceremony.Entity.Status;
import com.hus.ceremony.Service.MessageService;

@RestController
@RequestMapping("api/v1/message")
@CrossOrigin
public class MessageController {
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        // TODO Auto-generated constructor stub
        this.messageService = messageService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMessage(@RequestBody MessageDto messageDto) {

        Message creMessage = messageService.createMessage(messageDto);
        MessageDto creMessageDto = modelMapper.map(creMessage, MessageDto.class);
        return ResponseEntity.ok(creMessageDto);

    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<?> getMessageByName(@PathVariable String name) {

        List < Message > message = messageService.getMessageByNameList(name);
        if (message.size() == 0) {
            return ResponseEntity.ok(new Status("Message not found"));
        } else {
            List < MessageDto > messageDto = new java.util.ArrayList < MessageDto > ();
            for (Message m: message) {
                messageDto.add(modelMapper.map(m, MessageDto.class));
            }
            return ResponseEntity.ok(messageDto);
        }

    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllMessage() {

        return ResponseEntity.ok(messageService.getAllMessage());
    
    }

    @GetMapping("/get/time/{time}")
    public ResponseEntity<?> getMessageByTime(@PathVariable String time) {

        List < Message > message = messageService.getMessageByDateTimeList(time);
        if (message.size() == 0) {
            return ResponseEntity.ok(new Status("Message not found"));
        } else {
            List < MessageDto > messageDto = new java.util.ArrayList < MessageDto > ();
            for (Message m: message) {
                messageDto.add(modelMapper.map(m, MessageDto.class));
            }
            return ResponseEntity.ok(messageDto);
        }

    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getMessageById(@PathVariable int id) {

        Message message = messageService.getMessageById(id);
        if (message == null) {
            return ResponseEntity.ok(new Status("Message not found"));
        } else {
            MessageDto messageDto = modelMapper.map(message, MessageDto.class);
            return ResponseEntity.ok(messageDto);
        }

    }

    @PostMapping("/delete/id/{id}")
    public ResponseEntity<?> deleteMessageById(@PathVariable int id) {

        if (messageService.getMessageById(id) != null) {
            messageService.deleteMessageById(id);
        } else {
            return ResponseEntity.ok(new Status("Message not found"));
        }
        if (messageService.getMessageById(id) == null) {
            return ResponseEntity.ok(new Status("Message deleted"));
        } else {
            return ResponseEntity.ok(new Status("Message not deleted"));
        }

    }

    @PostMapping("/delete/all")
    public ResponseEntity<?> deleteAllMessage() {

        if (messageService.getAllMessage().size() != 0) {
            messageService.deleteAllMessage();
        } else {
            return ResponseEntity.ok(new Status("Not message"));
        }
        if (messageService.getAllMessage().size() == 0) {
            return ResponseEntity.ok(new Status("All message deleted"));
        } else {
            return ResponseEntity.ok(new Status("All message not deleted"));
        }

    }

    @PostMapping("/delete/list/{listId}")
    public ResponseEntity<?> deleteListMessage(@PathVariable String listId) {

        String[] listIdString = listId.split("-");
        List < Integer > listIdInt = new java.util.ArrayList < Integer > ();
        for (String s: listIdString) {
            listIdInt.add(Integer.parseInt(s));
        }
        messageService.deleteListMessage(listIdInt);
        for (int i : listIdInt) {
            if (messageService.getMessageById(i) != null) {
                return ResponseEntity.ok(new Status("Message not deleted"));
            }
        }
        return ResponseEntity.ok(new Status("Message deleted"));
        
    }

}
