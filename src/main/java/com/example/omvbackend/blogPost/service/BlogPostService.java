package com.example.omvbackend.blogPost.service;

import com.example.omvbackend.blogPost.model.BlogPost;
import com.example.omvbackend.blogPost.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository repository;

    // create
    public BlogPost create(BlogPost blogPost) {
        return repository.save(blogPost);
    }

    public List<BlogPost> getAll() {
        return repository.findAll();
    }
}
