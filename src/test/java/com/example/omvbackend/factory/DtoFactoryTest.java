package com.example.omvbackend.factory;

import com.example.omvbackend.user.DTOs.UserDTO;
import com.example.omvbackend.user.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DtoFactoryTest {

    @Test
    void fromUser() {
        // arrange
        String expectedUsername = "userName";
        String expectedPassword = "password";
        User user = new User(expectedUsername, expectedPassword);
        user.setId(1L);

        // act
        UserDTO result = DtoFactory.fromUser(user);

        // Assert
        assertInstanceOf(UserDTO.class, result);
        assertEquals(expectedUsername, result.getUsername());
        assertEquals(user.getId(), result.getId());
    }

    @Test
    void fromUserDto() {
        // arrange
        String expectedUsername = "userName";
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(expectedUsername);
        userDTO.setId(1L);

        // act
        User result = DtoFactory.fromUserDto(userDTO);

        // Assert
        assertInstanceOf(User.class, result);
        assertEquals(expectedUsername, result.getUsername());
        assertEquals(userDTO.getId(), result.getId());
    }

    @Test
    void fromUsers() {
        // arrange
        ArrayList<User> list = new ArrayList<>();

        String expectedUsername = "userName";
        String expectedPassword = "password";
        User user;
        for (int i = 0; i < 10; i++) {
            user = new User(expectedUsername + "i", expectedPassword);
            user.setId((long) i);
            list.add(user);
        }


        // act
        List<UserDTO> result = DtoFactory.fromUsers(list);

        // Assert
        assertInstanceOf(List.class, result);

        UserDTO userDto;
        for (int i = 0; i < result.size(); i++) {
            userDto = result.get(i);
            assertInstanceOf(UserDTO.class, userDto);
            assertEquals(expectedUsername + "i", userDto.getUsername());
            assertEquals(i, userDto.getId());
        }

    }
}