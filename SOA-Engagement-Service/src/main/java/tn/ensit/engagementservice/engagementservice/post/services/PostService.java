package tn.ensit.engagementservice.engagementservice.post.services;

import tn.ensit.engagementservice.engagementservice.post.entities.Post;
import tn.ensit.engagementservice.engagementservice.post.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post createPost(Long authorId, String content) {

        Post post = new Post(authorId, content);
        return repository.save(post);
    }

    public List<Post> getPostsByAuthor(Long authorId) {
        return repository.findByAuthorId(authorId);
    }

    public Post findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
