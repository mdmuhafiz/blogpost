package com.blogapp.service.impl;

import com.blogapp.entity.Comment;
import com.blogapp.entity.Post;
import com.blogapp.payload.CommentDto;
import com.blogapp.payload.PostDto;
import com.blogapp.payload.PostWithCommentDto;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepo;
    private PostRepository postRepo;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo, ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto,long postId) {
        Optional<Post> byId = postRepo.findById(postId);
        Post post = byId.get();
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);//here mapping is happend
        Comment savedComment = commentRepo.save(comment);
        CommentDto dto = mapToDto(savedComment);

        return dto;
    }


    public PostWithCommentDto getAllCommentsByPostId(long id){
        Post post = postRepo.findById(id).get();
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        List<Comment> comments = commentRepo.findByPostId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        PostWithCommentDto postWithCommentDto = new PostWithCommentDto();

        postWithCommentDto.setCommentDto(dtos);
        postWithCommentDto.setPost(dto);

        return postWithCommentDto;
    }


    Comment mapToEntity(CommentDto dto){
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;
    }
    CommentDto mapToDto(Comment comment){
     return modelMapper.map(comment,CommentDto.class);
    }
}
