package com.blogapp.payload;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min=3,message = "Title should be atleast 3 character")
    private String title;
    private String content;
    @NotEmpty
    @Size(min=3,message = "Description should be atleast 3 characters")
    private String description;
}
