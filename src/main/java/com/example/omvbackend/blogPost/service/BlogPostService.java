package com.example.omvbackend.blogPost.service;

import com.example.omvbackend.blogPost.model.BlogPost;
import com.example.omvbackend.blogPost.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository repository;

    // create
    public BlogPost create(BlogPost blogPost) {
        return repository.save(blogPost);
    }

    // Read
    public List<BlogPost> getAll() {
        return repository.findAll();
    }

    public Optional<BlogPost> get(Long id) {
        return repository.findById(id);
    }

    // delete
    public Boolean delete(Long id) {
        Optional<BlogPost> optionalBlogPost = repository.findById(id);
        if (optionalBlogPost.isEmpty()) return false;

        repository.delete(optionalBlogPost.get());
        return true;
    }
}
