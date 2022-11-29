package com.example.omvbackend.blogPost.service;

import com.example.omvbackend.blogPost.model.BlogPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Test
    void getAll(){
        // arrange
        int expectedLength = 4;
        service.create(new BlogPost(LocalDateTime.now(), "Ny musik er udkommet", "Dette er coveret"));
        service.create(new BlogPost(LocalDateTime.now(), "Ny musik er udkommet", "Dette er coveret"));

        // act
        List<BlogPost> result = service.getAll();

        // assert
        assertEquals(expectedLength, result.size());
    }

    @Test
    void getById(){

        // arrange
        String text = "Ny Post";
        String picture = "Nyt cover billede";
        LocalDateTime createdDate = LocalDateTime.now();
        long expectedID = service.create(new BlogPost(createdDate, text, picture)).getId();

        // act

        Optional<BlogPost> result = service.get(expectedID);

        // assert

        assertTrue(result.isPresent());

        assertEquals(expectedID, result.get().getId());
        assertEquals(text,result.get().getText());
        assertEquals(picture,result.get().getPicture());
        assertEquals(createdDate,result.get().getCreatedDate());
    }

    @Test
    void getByInvalidId(){
        // arrange
        String text = "Ny Post";
        String picture = "Nyt cover billede";
        LocalDateTime createdDate = LocalDateTime.now();
        long expectedID = service.create(new BlogPost(createdDate, text, picture)).getId();
        // act

        Optional<BlogPost> result = service.get(expectedID + 1);

        // assert

        assertTrue(result.isEmpty());
    }

    @Test
    void update_NoInfo() {
        // arrange
        String expectedText = "Expected text";
        String expectedPicture = "Expected Picture";
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        Long id = service.create(new BlogPost(expectedCreateDate, expectedText, expectedPicture)).getId();

        BlogPost updateElement = new BlogPost();
        updateElement.setId(id);

        // act
        Optional<BlogPost> result = service.update(updateElement);

        // assert
        assertTrue(result.isPresent());

        assertEquals(expectedText, result.get().getText());
        assertEquals(expectedPicture, result.get().getPicture());
        assertEquals(expectedCreateDate, result.get().getCreatedDate());
    }

    @Test
    void update_OnlyText() {
        // arrange
        String expectedText = "Expected text";
        String expectedPicture = "Expected Picture";
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        Long id = service.create(new BlogPost(expectedCreateDate, expectedText + "not the same", expectedPicture)).getId();

        BlogPost updateElement = new BlogPost();
        updateElement.setId(id);
        updateElement.setText(expectedText);

        // act
        Optional<BlogPost> result = service.update(updateElement);

        // assert
        assertTrue(result.isPresent());

        assertEquals(expectedText, result.get().getText());
        assertEquals(expectedPicture, result.get().getPicture());
        assertEquals(expectedCreateDate, result.get().getCreatedDate());
    }

    @Test
    void update_OnlyPicture() {
        // arrange
        String expectedText = "Expected text";
        String expectedPicture = "Expected Picture";
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        Long id = service.create(new BlogPost(expectedCreateDate, expectedText, expectedPicture + "not the same")).getId();

        BlogPost updateElement = new BlogPost();
        updateElement.setId(id);
        updateElement.setPicture(expectedPicture);

        // act
        Optional<BlogPost> result = service.update(updateElement);

        // assert
        assertTrue(result.isPresent());

        assertEquals(expectedText, result.get().getText());
        assertEquals(expectedPicture, result.get().getPicture());
        assertEquals(expectedCreateDate, result.get().getCreatedDate());
    }

    @Test
    void update_OnlyCreatedDate() {
        // arrange
        String expectedText = "Expected text";
        String expectedPicture = "Expected Picture";
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        Long id = service.create(new BlogPost(expectedCreateDate.minusDays(10), expectedText, expectedPicture)).getId();

        BlogPost updateElement = new BlogPost();
        updateElement.setId(id);
        updateElement.setCreatedDate(expectedCreateDate);

        // act
        Optional<BlogPost> result = service.update(updateElement);

        // assert
        assertTrue(result.isPresent());

        assertEquals(expectedText, result.get().getText());
        assertEquals(expectedPicture, result.get().getPicture());
        assertEquals(expectedCreateDate, result.get().getCreatedDate());
    }

    @Test
    void update_TextAndPicture() {
        // arrange
        String expectedText = "Expected text";
        String expectedPicture = "Expected Picture";
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        Long id = service.create(new BlogPost(expectedCreateDate, expectedText + "not the same", expectedPicture + "not the same")).getId();

        BlogPost updateElement = new BlogPost();
        updateElement.setId(id);
        updateElement.setText(expectedText);
        updateElement.setPicture(expectedPicture);

        // act
        Optional<BlogPost> result = service.update(updateElement);

        // assert
        assertTrue(result.isPresent());

        assertEquals(expectedText, result.get().getText());
        assertEquals(expectedPicture, result.get().getPicture());
        assertEquals(expectedCreateDate, result.get().getCreatedDate());
    }

    @Test
    void update_TextAndCreateDate() {
        // arrange
        String expectedText = "Expected text";
        String expectedPicture = "Expected Picture";
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        Long id = service.create(new BlogPost(expectedCreateDate.minusDays(10), expectedText + "not the same", expectedPicture)).getId();

        BlogPost updateElement = new BlogPost();
        updateElement.setId(id);
        updateElement.setText(expectedText);
        updateElement.setCreatedDate(expectedCreateDate);

        // act
        Optional<BlogPost> result = service.update(updateElement);

        // assert
        assertTrue(result.isPresent());

        assertEquals(expectedText, result.get().getText());
        assertEquals(expectedPicture, result.get().getPicture());
        assertEquals(expectedCreateDate, result.get().getCreatedDate());
    }

    @Test
    void update_PictureAndCreateDate() {
        // arrange
        String expectedText = "Expected text";
        String expectedPicture = "Expected Picture";
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        Long id = service.create(new BlogPost(expectedCreateDate.minusDays(10), expectedText, expectedPicture + "not the same")).getId();

        BlogPost updateElement = new BlogPost();
        updateElement.setId(id);
        updateElement.setPicture(expectedPicture);
        updateElement.setCreatedDate(expectedCreateDate);

        // act
        Optional<BlogPost> result = service.update(updateElement);

        // assert
        assertTrue(result.isPresent());

        assertEquals(expectedText, result.get().getText());
        assertEquals(expectedPicture, result.get().getPicture());
        assertEquals(expectedCreateDate, result.get().getCreatedDate());
    }


    @Test
    void update_AllInfo() {
        // arrange
        String expectedText = "Expected text";
        String expectedPicture = "Expected Picture";
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        Long id = service.create(new BlogPost(expectedCreateDate.minusDays(10), expectedText + "not the same", expectedPicture + "not the same")).getId();

        BlogPost updateElement = new BlogPost();
        updateElement.setId(id);
        updateElement.setText(expectedText);
        updateElement.setPicture(expectedPicture);
        updateElement.setCreatedDate(expectedCreateDate);

        // act
        Optional<BlogPost> result = service.update(updateElement);

        // assert
        assertTrue(result.isPresent());

        assertEquals(expectedText, result.get().getText());
        assertEquals(expectedPicture, result.get().getPicture());
        assertEquals(expectedCreateDate, result.get().getCreatedDate());
    }

    @Test
    void update_WrongId() {
        // arrange
        String expectedText = "Expected text";
        String expectedPicture = "Expected Picture";
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        Long id = 1 + service.create(new BlogPost(expectedCreateDate.minusDays(10), expectedText + "not the same", expectedPicture + "not the same")).getId();

        BlogPost updateElement = new BlogPost();
        updateElement.setId(id);

        // act
        Optional<BlogPost> result = service.update(updateElement);

        // assert
        assertTrue(result.isEmpty());
    }
}