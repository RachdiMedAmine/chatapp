package tn.ensit.chatservice.chatservice.message.entities;

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String channelId;

    private Long senderId;

    private Long receiverId;

    private String content;

    public Message() {}

    public Message(String channelId, Long senderId, Long receiverId, String content) {
        this.channelId = channelId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Long getSender() {
        return senderId;
    }

    public void setSender(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiver() {
        return receiverId;
    }

    public void setReceiver(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", channelId='" + channelId + '\'' +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                '}';
    }
}
