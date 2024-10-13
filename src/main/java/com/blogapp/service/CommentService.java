package com.blogapp.service;

import com.blogapp.payload.CommentDto;
import com.blogapp.payload.PostWithCommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    public PostWithCommentDto getAllCommentsByPostId(long postId);





}
