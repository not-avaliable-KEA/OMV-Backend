package com.example.omvbackend.factory;

import com.example.omvbackend.livevideo.DTOs.LiveVideoDTO;
import com.example.omvbackend.livevideo.model.LiveVideo;
import com.example.omvbackend.user.DTOs.UserDTO;
import com.example.omvbackend.user.model.User;
import com.example.omvbackend.work.DTOs.WorkDTO;
import com.example.omvbackend.work.model.Work;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

    /********
     *
     * Work method- DTO test
     *
     *******/

    @Test
    void fromWork() {
        //Arrange
        String expectedSingleName = "singleName";
        String expectedProducerName = "producerName";
        String expectedArtistName = "artiskName";
        String expectedDescription = "description";
        String expectedImage = "image";
        LocalDate expectedReleaseDate = LocalDate.now();
        Work work = new Work(expectedSingleName, expectedProducerName, expectedArtistName, expectedDescription, expectedImage, expectedReleaseDate);
        work.setId(1L);

        //Act
        WorkDTO result = DtoFactory.fromWork(work);

        //Assert
        assertInstanceOf(WorkDTO.class, result);
        assertEquals(work.getId(), result.getId());
        assertEquals(expectedImage, result.getImage());
        assertEquals(expectedArtistName, result.getArtist());
        assertEquals(expectedSingleName, result.getReleaseName());
        assertNotEquals(expectedReleaseDate, result.getReleaseDate());
    }

    @Test
    void fromWorks() {
        //Arrange
        ArrayList<Work> list = new ArrayList<>();

        String expectedRelease = "release";
        String expectedCredit = "credit";
        String expectedArtist = "artist";
        String expectedCommentary = "commentary";
        String expectedImage = "image";
        LocalDate expectedReleaseDate = LocalDate.of(2022, 10, 01);

        Work work;
        for (int i = 0; i < 10; i++) {
            work = new Work(expectedRelease + "i", expectedCredit + "i", expectedArtist + "i", expectedCommentary + "i", expectedImage + "i", expectedReleaseDate);
            work.setId(i);
            list.add(work);
        }

        //Act
        List<WorkDTO> result = DtoFactory.fromWorks(list);

        //Assert
        assertInstanceOf(List.class, result);

        WorkDTO coverDTO;
        for (int i = 0; i < result.size(); i++) {
            coverDTO = result.get(i);
            assertInstanceOf(WorkDTO.class, coverDTO);
            assertEquals(expectedImage + "i", coverDTO.getImage());
            assertEquals(expectedArtist + "i", coverDTO.getArtist());
            assertEquals(expectedRelease + "i", coverDTO.getReleaseName());
            assertEquals(expectedCommentary + "i", coverDTO.getCommentary());
            assertEquals(expectedCredit + "i", coverDTO.getCredit());
            assertEquals("2022-10-01", coverDTO.getReleaseDate());
            assertEquals(i, coverDTO.getId());
        }
    }

    @Test
    void fromWorkDTO(){
        //Arrange
        String expectedRelease = "singleName";
        String expectedArtist = "artistName";
        String expectedImage = "image";
        LocalDate expectedReleaseDate = LocalDate.now();
        WorkDTO workDTO = new WorkDTO();
        workDTO.setReleaseName(expectedRelease);
        workDTO.setArtist(expectedArtist);
        workDTO.setImage(expectedImage);
        workDTO.setReleaseDate(String.valueOf(expectedReleaseDate));
        workDTO.setId(1L);

        //Act
        Work result = DtoFactory.fromWorkDTO(workDTO);

        //Assert

        assertInstanceOf(Work.class, result);
        assertEquals(expectedRelease, result.getReleaseName());
        assertEquals(expectedArtist, result.getArtist());
        assertEquals(expectedImage, result.getImage());
        assertEquals(workDTO.getId(), result.getId());
        assertEquals(expectedReleaseDate, result.getReleaseDate());
    }

    /************
     * LiveVideo
     ************/
    @Test
    void fromLiveVideo() {
        // arrange
        LiveVideo video = new LiveVideo("url", "titel", "intro", LocalDate.of(2020, 11, 10));
        video.setId(1L);
        // act
        LiveVideoDTO result = DtoFactory.fromLiveVideo(video);

        // assert
        assertEquals(video.getUrl(), result.getUrl());
        assertEquals(video.getTitle(), result.getTitle());
        assertEquals(video.getIntro(), result.getIntro());
        assertEquals(video.getId(), result.getId());
        assertEquals("2020-11-10", result.getDate());
    }

    @Test
    void fromLiveVideoDTO() {
        // arrange
        LiveVideoDTO videoDTO = new LiveVideoDTO();
        videoDTO.setUrl("url");
        videoDTO.setTitle("title");
        videoDTO.setIntro("intro");
        videoDTO.setDate("2020-11-10");

        // act
        LiveVideo result = DtoFactory.fromLiveVideoDTO(videoDTO);

        // assert
        assertEquals(videoDTO.getUrl(), result.getUrl());
        assertEquals(videoDTO.getTitle(), result.getTitle());
        assertEquals(videoDTO.getIntro(), result.getIntro());
        assertEquals(result.getDate(), LocalDate.of(2020, 11, 10));
    }
}