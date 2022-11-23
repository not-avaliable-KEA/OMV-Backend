package com.example.omvbackend.user.service;

import com.example.omvbackend.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//den starter springboot serveren, den tester som det virker i applikationen, og ikke kun i klassen.
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void create(){
        //arrange - expected
        String expectedUsername = "username";
        String expectedPassword = "password";

        //act - result
        User result = userService.create(new User(expectedUsername, expectedPassword));

        //assert - asserting expected and result
        assertEquals(result.getUsername(), expectedUsername);
        assertEquals(result.getPassword(), expectedPassword);
        assertNotNull(result.getId());
    }









}