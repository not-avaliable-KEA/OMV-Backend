package com.example.omvbackend.blogPost.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogPostDTO {
    private Long id;

    private String createdDate;

    private String Text;

    private String picture;
}
