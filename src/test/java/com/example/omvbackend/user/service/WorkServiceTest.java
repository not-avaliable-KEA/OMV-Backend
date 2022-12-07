package com.example.omvbackend.user.service;

import com.example.omvbackend.work.model.Work;
import com.example.omvbackend.work.service.WorkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WorkServiceTest {
    @Autowired
    private WorkService workService;

    @Test
    void create(){
        //Arrange - expected
        String expectedreleaseName= "releaseName";
        String expectedCredit = "credit";
        String expectedArtist = "artist";
        String expectedCommentary = "commentary";
        String expectedImage = "image";
        LocalDate expectedReleaseDate = LocalDate.now();


        //Act - result
        Work result = workService.create(new Work(expectedreleaseName,expectedCredit,expectedArtist,expectedCommentary,expectedImage,expectedReleaseDate));

        //Assert
        //checking if singlename is set correctly
        assertEquals(result.getReleaseName(), expectedreleaseName);
        //checking if producername is set correctly
        assertEquals(result.getCredit(), expectedCredit);
        //checking if artistname is set correctly
        assertEquals(result.getArtist(),expectedArtist);
        //checking if description is set correctly
        assertEquals(result.getCommentary(),expectedCommentary);
        //checking if image is set correctly
        assertEquals(result.getImage(),expectedImage);
        //checking if releasedate is set correctly
        assertEquals(result.getReleaseDate(),expectedReleaseDate);
        //checking if Id is set correctly
        assertNotNull(result.getId());
        assertTrue(result.getId() > 0);
    }

    @Test
    void getAll(){
        //Arrange
        workService.create(new Work("test","test","test","test","test",LocalDate.now()));
        int expectedsize = 2; //2 cause we created a new one in the delete method

        //Act
        List<Work> result = workService.getAll();

        //Assert
        assertEquals(expectedsize,result.size());

        //checks that all users are unique in the result array
        ArrayList<Work> workChecked = new ArrayList<>();
        for (Work w : result) { //forloop that runs through the result list
            assertFalse(workChecked.contains(w)); //we expect a false, that the new list doesnt contain work w
            workChecked.add(w); //if statement is false, we add user w - if true the test fails
        }
    }

    @Test
    void get_validId(){
        //Arrange
        Work expectedWork = workService.create(new Work("test","test","test","test","test",LocalDate.now()));

        //Act
        Optional<Work> result = workService.get(expectedWork.getId());

        //Assert
        //we check if there is a user
        assertTrue(result.isPresent());
        //
        workCheckEquals(expectedWork,result.get());
    }

    @Test
    void update(){
        //Arrange
        Work created = workService.create(new Work("test","test","test","test","test",LocalDate.now()));
        Work updateTo = new Work();
        updateTo.setId(created.getId());
        updateTo.setReleaseName("new Test");
        updateTo.setCredit("new Test");
        updateTo.setArtist("new Test");
        updateTo.setCommentary("new Test");
        updateTo.setImage("new Test");
        updateTo.setReleaseDate(LocalDate.now());


        //Act
        Work result = workService.update(updateTo);

        //Assert
        // checks that updateTo and created is not the same object.
        assertNotEquals(updateTo,created);
        //checks the basics
        assertEquals(updateTo.getId(),result.getId());
        assertEquals(updateTo.getReleaseDate(),result.getReleaseName());
        assertEquals(updateTo.getArtist(),result.getArtist());
        assertEquals(updateTo.getCommentary(),result.getCommentary());
        assertEquals(updateTo.getImage(),result.getImage());
        assertEquals(updateTo.getReleaseDate(),result.getReleaseDate());
    }

    @Test
    void delete(){
        //Arrange
        //Create a work to delete
        Work expectedWork = workService.create(new Work("test","test","test","test","test",LocalDate.now()));
        //get the expected lenght of users, minus 1, since we expect to remove the one we just created
        int expectedLengthOfWorks = workService.getAll().size() - 1;

        //Act
        boolean result = workService.delete(expectedWork.getId());
        int currentLengthOfWorks = workService.getAll().size();

        //Assert
        assertTrue(result);
        assertEquals(expectedLengthOfWorks,currentLengthOfWorks);
    }

    /******
     *                      *
     * Helper functions     *
     *                      *
     ******/

    //function to compare two works, that are expected to be the same
    void workCheckEquals(Work expected, Work result){
        assertEquals(expected.getId(),result.getId());
    }
}

