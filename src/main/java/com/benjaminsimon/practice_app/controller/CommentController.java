package com.benjaminsimon.practice_app.controller;

import com.benjaminsimon.practice_app.dto.CommentDto;
import com.benjaminsimon.practice_app.exception.PostNotFoundException;
import com.benjaminsimon.practice_app.model.Comment;
import com.benjaminsimon.practice_app.model.Post;
import com.benjaminsimon.practice_app.repository.PostRepository;
import com.benjaminsimon.practice_app.service.CommentService;
import com.benjaminsimon.practice_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto) {
        Comment comment = commentService.createComment(commentDto);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@RequestBody CommentDto commentDto, @PathVariable Long id) {
        Comment comment = commentService.updateComment(commentDto, id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsForPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        Comment comment = commentService.getById(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
