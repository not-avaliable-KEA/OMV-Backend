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

    // update
    public Optional<BlogPost> update(BlogPost blogPost) {
        Optional<BlogPost> optionalBlogPost = repository.findById(blogPost.getId());
        if (optionalBlogPost.isEmpty()) return Optional.empty();

        if(blogPost.getText() != null && !blogPost.getText().trim().isEmpty())
            optionalBlogPost.get().setText(blogPost.getText().trim());

        if (blogPost.getPicture() != null && !blogPost.getPicture().trim().isEmpty())
            optionalBlogPost.get().setPicture(blogPost.getPicture().trim());

        if (blogPost.getTitle() != null && !blogPost.getTitle().trim().isEmpty())
            optionalBlogPost.get().setTitle(blogPost.getTitle().trim());

        if (blogPost.getCreatedDate() != null)
            optionalBlogPost.get().setCreatedDate(blogPost.getCreatedDate());

        return Optional.of(repository.save(optionalBlogPost.get()));
    }

    // delete
    public Boolean delete(Long id) {
        Optional<BlogPost> optionalBlogPost = repository.findById(id);
        if (optionalBlogPost.isEmpty()) return false;

        repository.delete(optionalBlogPost.get());
        return true;
    }
}
