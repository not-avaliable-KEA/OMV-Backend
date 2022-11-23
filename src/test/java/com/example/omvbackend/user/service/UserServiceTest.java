package com.example.omvbackend.user.service;

import com.example.omvbackend.user.model.User;
import org.junit.jupiter.api.BeforeEach;
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

        //act - result
        User result = userService.create(new User(expectedUsername, expectedPassword));

        //assert
        // checking that the username is set correctly
        assertEquals(result.getUsername(), expectedUsername);

        // checking that the password gets set correctly
        assertNotEquals(expectedPassword, result.getPassword());
        assertEquals(64, result.getPassword().length());

        // checking that the salt is set correctly
        assertEquals(16, result.getSalt().length());

        // checking that the id is set correctly
        assertNotNull(result.getId());
        assertTrue(result.getId() > 0);

    }

    @Test
    void getAll() {
        //arrange
        userService.create(new User("tutu", "tamtam"));
        int expectedSize = 3;

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

    @Test
    void checkLogin_InvalidLogin_InvalidPassword() {
        // arrange
        String username = "username1";
        String password = "password";
        userService.create(new User(username, password));

        // act
        User result = userService.checkLogin(new User(username, "not the correct password!"));

        // assert
        assertNull(result);
    }

    @Test
    void checkLogin_InvalidLogin_InvalidUsername() {
        // arrange
        String username = "username2";
        String password = "password";
        userService.create(new User(username, password));

        // act
        User result = userService.checkLogin(new User("not the right password", password));

        // assert
        assertNull(result);
    }

    @Test
    void checkLogin_valid() {
        // arrange
        String username = "username3";
        String password = "password";
        User expectedUser = userService.create(new User(username, password));

        // act
        User result = userService.checkLogin(new User(username, password));

        // assert
        userCheckEquals(expectedUser, result);
    }


    /************
     *
     * Helper functions
     *
     **************/

    // function to compare two users, that are expected to be the same
    void userCheckEquals(User expected,User result) {
        assertEquals(expected.getId(),result.getId());
        assertEquals(expected.getUsername(),result.getUsername());
        assertEquals(expected.getPassword(),result.getPassword());
        assertEquals(expected.getSalt(),result.getSalt());
    }

}