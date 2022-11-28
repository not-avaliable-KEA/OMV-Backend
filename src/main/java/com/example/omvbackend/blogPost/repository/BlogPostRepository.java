package com.example.omvbackend.blogPost.repository;

import com.example.omvbackend.blogPost.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository  extends JpaRepository<BlogPost, Long> {
}
