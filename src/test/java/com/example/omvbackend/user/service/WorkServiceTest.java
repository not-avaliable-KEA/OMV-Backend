package com.example.omvbackend.user.service;

import com.example.omvbackend.work.model.Work;
import com.example.omvbackend.work.service.WorkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        String expectedSingleName= "singleName";
        String expectedProducerName = "producerName";
        String expectedArtistName = "artiskName";
        String expectedDescription = "description";
        String expectedImage = "image";
        LocalDateTime expectedReleaseDate = LocalDateTime.now();
        String expectedWriter = "writer";
        String expectedMaster = "master";

        //Act - result
        Work result = workService.create(new Work(expectedSingleName,expectedProducerName,expectedArtistName,expectedDescription,expectedImage,expectedReleaseDate,expectedWriter,expectedMaster));

        //Assert
        //checking if singlename is set correctly
        assertEquals(result.getSingleName(), expectedSingleName);
        //checking if producername is set correctly
        assertEquals(result.getProducerName(), expectedProducerName);
        //checking if artistname is set correctly
        assertEquals(result.getArtistName(),expectedArtistName);
        //checking if description is set correctly
        assertEquals(result.getDescription(),expectedDescription);
        //checking if image is set correctly
        assertEquals(result.getImage(),expectedImage);
        //checking if releasedate is set correctly
        assertEquals(result.getReleaseDate(),expectedReleaseDate);
        //checking if writer is set correctly
        assertEquals(result.getWriter(),expectedWriter);
        //checking if master is set correctly
        assertEquals(result.getMaster(),expectedMaster);
        //checking if Id is set correctly
        assertNotNull(result.getId());
        assertTrue(result.getId() > 0);
    }

    @Test
    void getAll(){
        //Arrange
        workService.create(new Work("test","test","test","test","test",LocalDateTime.now(),"test","test"));
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
        Work expectedWork = workService.create(new Work("test","test","test","test","test",LocalDateTime.now(),"test","test"));

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
        Work created = workService.create(new Work("test","test","test","test","test",LocalDateTime.now(),"test","test"));
        Work updateTo = new Work();
        updateTo.setId(created.getId());
        updateTo.setSingleName("new Test");
        updateTo.setProducerName("new Test");
        updateTo.setArtistName("new Test");
        updateTo.setDescription("new Test");
        updateTo.setImage("new Test");
        updateTo.setReleaseDate(LocalDateTime.now());
        updateTo.setWriter("new Test");
        updateTo.setMaster("new Test");

        //Act
        Work result = workService.update(updateTo);

        //Assert
        // checks that updateTo and created is not the same object.
        assertNotEquals(updateTo,created);
        //checks the basics
        assertEquals(updateTo.getId(),result.getId());
        assertEquals(updateTo.getSingleName(),result.getSingleName());
        assertEquals(updateTo.getArtistName(),result.getArtistName());
        assertEquals(updateTo.getDescription(),result.getDescription());
        assertEquals(updateTo.getImage(),result.getImage());
        assertEquals(updateTo.getReleaseDate(),result.getReleaseDate());
        assertEquals(updateTo.getWriter(),result.getWriter());
        assertEquals(updateTo.getMaster(),result.getMaster());
    }

    @Test
    void delete(){
        //Arrange
        //Create a work to delete
        Work expectedWork = workService.create(new Work("test","test","test","test","test",LocalDateTime.now(),"test","test"));
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
