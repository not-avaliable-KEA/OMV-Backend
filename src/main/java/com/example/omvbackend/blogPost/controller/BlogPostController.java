package com.example.omvbackend.blogPost.controller;

import com.example.omvbackend.blogPost.DTOs.BlogPostDTO;
import com.example.omvbackend.blogPost.model.BlogPost;
import com.example.omvbackend.blogPost.service.BlogPostService;
import com.example.omvbackend.factory.DtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<BlogPostDTO>> getAll(){
        return ResponseEntity.ok().body(DtoFactory.fromBlogPosts(service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDTO> get(@PathVariable Long id){
        Optional<BlogPost> blogPostOptional = service.get(id);

        BlogPost blogPost = null;
        if(blogPostOptional.isPresent())  blogPost = blogPostOptional.get();

        return ResponseEntity.ok().body(DtoFactory.fromBlogPost(blogPost));
    }
}
