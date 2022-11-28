package com.example.omvbackend.blogPost.controller;

import com.example.omvbackend.blogPost.DTOs.BlogPostDTO;
import com.example.omvbackend.blogPost.model.BlogPost;
import com.example.omvbackend.blogPost.service.BlogPostService;
import com.example.omvbackend.factory.DtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "https://white-sand-06fa66003.2.azurestaticapps.net/"}, allowCredentials = "true")
@RequestMapping("api/v1/blogpost")
public class BlogPostController {

    @Autowired
    private BlogPostService service;

    @PostMapping
    public ResponseEntity<BlogPost> create(@Valid @RequestBody BlogPostDTO blogPost){


        BlogPost blog = service.create(DtoFactory.fromBlogPostDTO(blogPost));
        System.out.println(blog.getId());

        return ResponseEntity.ok().body(blog);
    }


}
