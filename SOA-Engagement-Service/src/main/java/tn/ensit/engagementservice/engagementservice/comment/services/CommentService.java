package tn.ensit.engagementservice.engagementservice.comment.services;

import tn.ensit.engagementservice.engagementservice.comment.entities.Comment;
import tn.ensit.engagementservice.engagementservice.post.entities.Post;
import tn.ensit.engagementservice.engagementservice.comment.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import tn.ensit.engagementservice.engagementservice.post.services.PostService;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository repository;
    private final PostService postService;

    public CommentService(CommentRepository repository, PostService postService) {
        this.repository = repository;
        this.postService = postService;
    }

    public Comment addComment(Long commenterId, Long postId, String content) {
        Post post = postService.findById(postId);
        return repository.save(
                new Comment(commenterId, post, content)
        );
    }

    public List<Comment> getCommentsByPost(Long postId) {
        Post post = postService.findById(postId);
        return repository.findByPost(post);
    }
}