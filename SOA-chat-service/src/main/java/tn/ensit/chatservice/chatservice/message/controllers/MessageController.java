package tn.ensit.chatservice.chatservice.message.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.ensit.chatservice.chatservice.message.dto.MessageDto;
import tn.ensit.chatservice.chatservice.message.entities.Message;
import tn.ensit.chatservice.chatservice.message.services.MessageService;

import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> createMessage(@RequestBody MessageDto dto) {
        Message savedMessage = messageService.saveMessage(dto);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/between/{senderId}/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesBetween(@PathVariable Long senderId, @PathVariable Long receiverId) {
        List<Message> messages = messageService.getMessagesBetween(senderId, receiverId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        var message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }
}
