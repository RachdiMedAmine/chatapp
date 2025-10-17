package tn.ensit.engagementservice.engagementservice.like.entities;

import jakarta.persistence.*;
import tn.ensit.engagementservice.engagementservice.post.entities.Post;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime timestamp;

    protected Like() {
    }

    public Like(Long userId, Post post) {
        this.userId = userId;
        this.post = post;
        this.timestamp = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", user=" + userId +
                ", post=" + post.getId() +
                '}';
    }
}