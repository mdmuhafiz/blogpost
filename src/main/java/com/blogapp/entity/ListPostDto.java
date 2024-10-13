package com.blogapp.entity;

import com.blogapp.payload.PostDto;
import lombok.Data;

import java.util.List;
@Data
public class ListPostDto {
    private List<PostDto> postDto;
    private int totalPages;
    private int totalElements;
    private boolean lastPage;
    private boolean firstPage;
    private int pageNumber;


}
