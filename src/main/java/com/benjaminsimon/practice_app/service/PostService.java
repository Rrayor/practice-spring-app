package com.benjaminsimon.practice_app.service;

import com.benjaminsimon.practice_app.dto.PostDto;
import com.benjaminsimon.practice_app.exception.PostNotFoundException;
import com.benjaminsimon.practice_app.model.Post;
import com.benjaminsimon.practice_app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public Post createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreatedAt(Instant.now());
        post.setUpdatedAt(Instant.now());

        return postRepository.save(post);
    }

    public Post updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post could not be found"));

        boolean updated = false;

        if(postDto.getContent() != null && postDto.getContent().length() > 0) {
            post.setContent(postDto.getContent());
            updated = true;
        }

        if(postDto.getTitle() != null && postDto.getTitle().length() > 0) {
            post.setTitle(postDto.getTitle());
            updated = true;
        }

        if(updated)
            post.setUpdatedAt(Instant.now());

        return post;
    }

    public boolean deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post could not be found"));

        postRepository.delete(post);

        return true;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post could not be found"));
    }
}
