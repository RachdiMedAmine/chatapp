package tn.ensit.engagementservice.engagementservice.post.controllers;

import tn.ensit.engagementservice.engagementservice.post.dto.PostDto;
import tn.ensit.engagementservice.engagementservice.post.entities.Post;
import tn.ensit.engagementservice.engagementservice.post.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto) {
        Post post = postService.createPost(postDto.getAuthorId(), postDto.getContent());
        return ResponseEntity.ok(post);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userId) {
        List<Post> posts = postService.getPostsByAuthor(userId);
        return ResponseEntity.ok(posts);
    }
}
