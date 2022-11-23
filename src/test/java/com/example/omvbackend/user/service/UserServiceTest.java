package com.example.omvbackend.user.service;

import com.example.omvbackend.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void getAll() {
        //arrange
        userService.create(new User("tutu", "tamtam"));
        int expectedSize = 2;

        //act
        List<User> result = userService.getAll();

        //assert
        assertEquals(expectedSize,result.size());

        //checks that all users are unique in the result array
        ArrayList<User> usersChecked = new ArrayList<>();
        for (User u : result) {                    //forloop that runs though the result list,
            assertFalse(usersChecked.contains(u)); //we expect at false, that the new list doesn contain user u
            usersChecked.add(u);                   //if the statement is false, we add user u - if true the test fails.
        }
    }

}