package com.example.omvbackend.livevideo.service;
import com.example.omvbackend.blogPost.model.BlogPost;
import com.example.omvbackend.livevideo.model.LiveVideo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LiveVideoServiceTest {

    @Autowired
    private LiveVideoService service;

    //create test

    @Test
    void create() {
        // arrange
        String url = "localprut.dk";
        String title = "camellaalicious";
        String intro = "bla bla";
        LocalDate createdDate = LocalDate.now();

        // act
        LiveVideo result = service.create(new LiveVideo(url, title, intro, createdDate));

        // assert
        assertNotNull(result);
        assertEquals(url, result.getUrl());
        checkLocalDateTimeEquals(createdDate, result.getDate());
        assertEquals(title, result.getTitle());
        assertEquals(intro, result.getIntro());

        assertNotNull(result.getId());
        assertTrue(result.getId() > 0);
    }
    //get test
    @Test
    void getById(){
        // arrange
        String url = "yasyas.com";
        String title = "title";
        String intro = "det er bare så nice";
        LocalDate createdDate = LocalDate.now();
        long expectedID = service.create(new LiveVideo(url, title, intro,createdDate)).getId();

        // act
        Optional<LiveVideo> result = service.get(expectedID);

        // assert
        assertTrue(result.isPresent());

        assertEquals(expectedID, result.get().getId());
        assertEquals(url,result.get().getUrl());
        assertEquals(title, result.get().getTitle());
        checkLocalDateTimeEquals(createdDate,result.get().getDate());
    }

    //getall test
    @Test
    void getAll(){

        //arrange
        LiveVideo expected1 = service.create(new LiveVideo("Tutu.com", "mathuhu", "det er bare så fint det hele", LocalDate.EPOCH));
        LiveVideo expected2 = service.create(new LiveVideo("Sofia.com","SoSo","yas der er så fint",LocalDate.EPOCH));
        List<LiveVideo> expectedList = new ArrayList<>();
        expectedList.add(expected1);
        expectedList.add(expected2);

        //act
        List<LiveVideo> result = service.getAll();

        //assert
        assertEquals(expectedList.size(), result.size());
    }

    //update test

    //delete test
    @Test
    void delete(){
        //arrange
        LiveVideo expected = service.create(new LiveVideo("Lasi.com","lasia","heej",LocalDate.EPOCH));

        //act
        Boolean result = service.delete(expected.getId());

        //assert
        assertTrue(result);
        assertTrue(service.get(expected.getId()).isEmpty());

    }

    void checkLocalDateTimeEquals(LocalDate expected, LocalDate actual) {
        assertEquals(expected.format(DateTimeFormatter.ISO_LOCAL_DATE), actual.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }



}