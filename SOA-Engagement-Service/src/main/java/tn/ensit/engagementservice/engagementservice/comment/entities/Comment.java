package tn.ensit.engagementservice.engagementservice.comment.entities;

import jakarta.persistence.*;
import tn.ensit.engagementservice.engagementservice.post.entities.Post;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Still valid JPA relation within the same service
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Only store the ID of the user from UserService
    @Column(name = "commenter_id", nullable = false)
    private Long commenterId;

    private String content;
    private LocalDateTime timestamp;

    public Comment() {
    }

    public Comment(Long commenterId, Post post, String content) {
        this.commenterId = commenterId;
        this.post = post;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(Long commenterId) {
        this.commenterId = commenterId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + (post != null ? post.getId() : null) +
                ", commenterId=" + commenterId +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
