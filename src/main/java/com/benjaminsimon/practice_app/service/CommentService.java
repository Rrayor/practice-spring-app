package com.benjaminsimon.practice_app.service;

import com.benjaminsimon.practice_app.dto.CommentDto;
import com.benjaminsimon.practice_app.exception.CommentNotFoundException;
import com.benjaminsimon.practice_app.exception.PostNotFoundException;
import com.benjaminsimon.practice_app.model.Comment;
import com.benjaminsimon.practice_app.model.Post;
import com.benjaminsimon.practice_app.repository.CommentRepository;
import com.benjaminsimon.practice_app.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private PostService postService;

    public Comment createComment(CommentDto commentDto) {
        Post post = postService.getPostById(commentDto.getPostId());

        //Post post = getPost(commentDto.getPostId());
        Comment comment = new Comment();

        comment.setContent(commentDto.getContent());
        comment.setPost(post);
        comment.setCreatedAt(Instant.now());
        comment.setUpdatedAt(Instant.now());

        return commentRepository.save(comment);
    }

    public Comment updateComment(CommentDto commentDto, Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment could not be found"));

        boolean updated = false;

        if(commentDto.getContent() != null && commentDto.getContent().length() > 0) {
            comment.setContent(commentDto.getContent());
            updated = true;
        }

        if(updated)
            comment.setUpdatedAt(Instant.now());

        return comment;
    }

    public boolean deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment could not be found"));

        commentRepository.delete(comment);

        return true;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        Post post = getPost(postId);

        return post.getComments();
    }

    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment could not be found"));
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post could not be found"));
    }
}
