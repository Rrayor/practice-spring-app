package com.benjaminsimon.practice_app.controller;

import com.benjaminsimon.practice_app.dto.PostDto;
import com.benjaminsimon.practice_app.model.Post;
import com.benjaminsimon.practice_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto) {
        Post post = postService.createPost(postDto);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody PostDto postDto, @PathVariable Long id) {
        Post post = postService.updatePost(postDto, id);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        boolean success = postService.deletePost(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
