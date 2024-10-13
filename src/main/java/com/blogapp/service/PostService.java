package com.blogapp.service;

import com.blogapp.entity.ListPostDto;
import com.blogapp.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    void deletePost(long id);
    ListPostDto fetchAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
    public PostDto getPostById(long id);
}
