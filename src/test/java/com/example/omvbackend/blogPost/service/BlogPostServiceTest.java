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

    @Test
    void delete_ValidId() {
        // arrange
        BlogPost toBeDeleted = service.create(new BlogPost(LocalDateTime.now(),
                                                      "To be deleted",
                                                    "Picture to be deleted"));

        // act
        boolean result = service.delete(toBeDeleted.getId());

        // assert
        assertTrue(result);

        assertFalse(service.get(toBeDeleted.getId()).isPresent());
    }

    @Test
    void delete_InvalidId() {
        // arrange
        long toBeDeleted = service.create(new BlogPost(LocalDateTime.now(),
                "To be deleted",
                "Picture to be deleted"))
                .getId();

        // act
        boolean result = service.delete(toBeDeleted + 1);

        // assert
        assertFalse(result);

        assertFalse(service.get(toBeDeleted + 1).isPresent());
        assertTrue(service.get(toBeDeleted).isPresent());
    }

}