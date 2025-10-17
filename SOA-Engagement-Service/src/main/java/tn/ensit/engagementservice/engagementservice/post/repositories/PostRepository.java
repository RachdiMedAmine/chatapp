package tn.ensit.engagementservice.engagementservice.post.repositories;

import org.springframework.stereotype.Repository;
import tn.ensit.engagementservice.engagementservice.post.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthorId(Long authorId);
}