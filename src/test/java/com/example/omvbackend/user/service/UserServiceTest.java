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
//it starts the springboot server - it tests the way it works in the applikation, og not just the class.

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

    @Test
    void delete_invalidId() {
        // arrange
        // create user to delete
        User expectedUser = userService.create(new User("deleteUser", "password"));

        // get the expected length of users, minus 1 since we expect to remove the one we just created
        int expectedLengthOfUsers = userService.getAll().size();


        // act
        boolean result = userService.delete(expectedUser.getId() + 1);
        int currentLengthOfUsers = userService.getAll().size();


        // assert
        assertFalse(result);
        assertEquals(expectedLengthOfUsers, currentLengthOfUsers);
    }

    @Test
    void delete_validId() {
        // arrange
        // create user to delete
        User expectedUser = userService.create(new User("deleteUser", "password"));

        // get the expected length of users, minus 1 since we expect to remove the one we just created
        int expectedLengthOfUsers = userService.getAll().size() - 1;


        // act
        boolean result = userService.delete(expectedUser.getId());
        int currentLengthOfUsers = userService.getAll().size();


        // assert
        assertTrue(result);
        assertEquals(expectedLengthOfUsers, currentLengthOfUsers);
    }

    @Test
    void update_username(){
        //arrange
        User createdUser = userService.create(new User("yas","naaj"));
        String oldSalt = createdUser.getSalt();
        String oldPassword = createdUser.getPassword();


        String expectedName= "tis";
        User forUpdatedUser = new User();
        forUpdatedUser.setUsername(expectedName);

        //act
        User result = userService.update(forUpdatedUser);

        //assert
        //checks that the result is not null
        assertNotNull(result);
        //checks that the username has been set correctly
        assertEquals(expectedName,result.getUsername());

        // checks that the password has not been changed
        assertEquals(oldSalt, result.getSalt());
        assertEquals(oldPassword, result.getPassword());

        // checks that we can login with the new password
        User loginCheck = new User(expectedName, oldPassword);
        assertEquals(userService.checkLogin(loginCheck).getId(), createdUser.getId());
    }

    @Test
    void update_password(){
        //arrange
        User createdUser = userService.create(new User("yas","naaj"));
        String oldUsername = createdUser.getUsername();
        String oldSalt = createdUser.getSalt();
        String oldPassword = createdUser.getPassword();

        String expectedPassword= "movie";
        User forUpdatedUser = new User();
        forUpdatedUser.setPassword(expectedPassword);


        //act
        User result = userService.update(forUpdatedUser);


        //assert
        //checks that we do not get null back
        assertNotNull(result);

        //checks that the username is still the same
        assertEquals(oldUsername, result.getUsername());

        // checks that the password is not the same as the old one and that it follows
        assertNotEquals(oldPassword, result.getPassword());
        assertEquals(64, result.getPassword().length());

        // checks that the salt has been set, and it has the correct length
        assertNotEquals(oldSalt, result.getSalt());
        assertEquals(16, result.getSalt().length());

        // checks that we can login with the new password
        User loginCheck = new User(oldUsername, expectedPassword);
        assertEquals(userService.checkLogin(loginCheck).getId(), createdUser.getId());
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