package tn.ensit.chatservice.chatservice.message.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.ensit.chatservice.chatservice.message.entities.Message;

import java.util.List;


// We just have to extend the JPARepository then Spring Data will automatically create the CRUD operations
// on its own (see the usage of the repository in the service)

// This annotation is used to declare a Repository
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}