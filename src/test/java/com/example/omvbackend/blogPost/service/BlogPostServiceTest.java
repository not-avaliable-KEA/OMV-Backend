package com.example.omvbackend.blogPost.service;

import com.example.omvbackend.blogPost.model.BlogPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogPostServiceTest {

    @Autowired
    private BlogPostService service;

    @Test
    void create() {
        // arrange
        String text = "this is a text";
        String picture = "this is a picture";
        LocalDateTime createdDate = LocalDateTime.now();

        // act
        BlogPost result = service.create(new BlogPost(createdDate, text, picture));

        System.out.println(result.getId());
        // assert
        assertNotNull(result);

        assertEquals(createdDate, result.getCreatedDate());
        assertEquals(text, result.getText());
        assertEquals(picture, result.getPicture());

        assertNotNull(result.getId());
        assertTrue(result.getId() > 0);
    }

}