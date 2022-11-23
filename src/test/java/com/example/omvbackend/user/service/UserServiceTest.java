package com.example.omvbackend.user.service;

import com.example.omvbackend.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        User expectedUser = new User(expectedUsername, expectedPassword);
        expectedUser.setId(1L);

        //act - result
        User result = userService.create(new User(expectedUsername, expectedPassword));

        //assert - asserting expected and result

        userCheckEquals(expectedUser, result);
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

    @Test
    void get_ValidId(){
        //arrange
        User expectedUser = userService.create(new User("camcam","yaas"));

        //act
        Optional<User> result = userService.get(expectedUser.getId());

        //assert
        //We check if the there is a user.
        assertTrue(result.isPresent());
        //then we check what it is.
        userCheckEquals(expectedUser, result.get());
    }

    @Test
    void get_NotValidId(){
        //arrange
        long invalidId = -1;

        //act
        Optional<User> result = userService.get(invalidId); //declare a optional user, og set it to be invalid on purpose.

        //assert
        assertTrue(result.isEmpty()); //we expect result is empty, that id is invalid.
    }

    // function to compare two users, that are expected to be the same
    void userCheckEquals(User expected,User result) {
        assertEquals(expected.getId(),result.getId());
        assertEquals(expected.getUsername(),result.getUsername());
        assertEquals(expected.getPassword(),result.getPassword());
    }

}