package tn.ensit.chatservice.chatservice.message.services;

import org.springframework.stereotype.Service;
import tn.ensit.chatservice.chatservice.message.dto.MessageDto;
import tn.ensit.chatservice.chatservice.message.entities.Message;
import tn.ensit.chatservice.chatservice.message.repositories.MessageRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MessageService {

    private static final Logger LOGGER = Logger.getLogger(MessageService.class.getName());

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(MessageDto dto) {
        if (dto.getSenderId() == null || dto.getReceiverId() == null || dto.getContent() == null) {
            String msg = "Invalid message: senderId, receiverId, and content must not be null";
            LOGGER.severe(msg);
            throw new IllegalArgumentException(msg);
        }

        Message message = new Message(
                dto.getChannelId(),
                dto.getSenderId(),
                dto.getReceiverId(),
                dto.getContent()
        );
        return messageRepository.save(message);
    }

    public List<Message> getMessagesBetween(Long senderId, Long receiverId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
