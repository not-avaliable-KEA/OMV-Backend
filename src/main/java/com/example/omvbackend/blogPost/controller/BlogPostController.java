package com.example.omvbackend.blogPost.controller;

import com.example.omvbackend.blogPost.DTOs.BlogPostDTO;
import com.example.omvbackend.blogPost.model.BlogPost;
import com.example.omvbackend.blogPost.service.BlogPostService;
import com.example.omvbackend.factory.DtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public ResponseEntity<BlogPostDTO> create(HttpSession session, @Valid @RequestBody BlogPostDTO blogPost){
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(
                    DtoFactory.fromBlogPost(
                        service.create(DtoFactory.fromBlogPostDTO(blogPost))));
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

    @PatchMapping("/{id}")
    public ResponseEntity<BlogPost> update(HttpSession session,
                                           @Valid @RequestBody BlogPostDTO blogPostDTO,
                                           @PathVariable long id) {
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);

        BlogPost blogPost = DtoFactory.fromBlogPostDTO(blogPostDTO);

        blogPost.setId(id);

        Optional<BlogPost> result = service.update(blogPost);

        if (result.isPresent()) {
            return ResponseEntity.ok().body(result.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(HttpSession session, @PathVariable long id) {
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(service.delete(id));
    }
}
