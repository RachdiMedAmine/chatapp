package tn.ensit.engagementservice.engagementservice.comment.repositories;

import org.springframework.stereotype.Repository;
import tn.ensit.engagementservice.engagementservice.comment.entities.Comment;
import tn.ensit.engagementservice.engagementservice.post.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
