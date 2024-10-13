package com.blogapp.controller;

import com.blogapp.payload.CommentDto;
import com.blogapp.payload.PostWithCommentDto;
import com.blogapp.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createCommentTosaveInDB(@RequestBody CommentDto commentDto, @PathVariable long postId){
        CommentDto dto = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostWithCommentDto> getAllCommentsByPostId(@PathVariable long postId){
        PostWithCommentDto postwithcommentDtos = commentService.getAllCommentsByPostId(postId);
        return  new ResponseEntity<>(postwithcommentDtos,HttpStatus.OK);
    }

}
