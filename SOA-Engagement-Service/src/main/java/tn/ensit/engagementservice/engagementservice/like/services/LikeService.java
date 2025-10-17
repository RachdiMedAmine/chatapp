package tn.ensit.engagementservice.engagementservice.like.services;

import org.springframework.stereotype.Service;
import tn.ensit.engagementservice.engagementservice.like.entities.Like;
import tn.ensit.engagementservice.engagementservice.like.repositories.LikeRepository;
import tn.ensit.engagementservice.engagementservice.post.entities.Post;
import tn.ensit.engagementservice.engagementservice.post.services.PostService;

import java.util.List;

@Service
public class LikeService {

    private final LikeRepository repository;
    private final PostService postService;

    public LikeService(LikeRepository repository, PostService postService) {
        this.repository = repository;
        this.postService = postService;
    }

    public Like likePost(Long userId, Long postId) {
        Post post = postService.findById(postId);
        Like like = new Like(userId, post);
        return repository.save(like);
    }

    public List<Like> getLikesByPost(Long postId) {
        Post post = postService.findById(postId);
        return repository.findByPost(post);
    }
}
