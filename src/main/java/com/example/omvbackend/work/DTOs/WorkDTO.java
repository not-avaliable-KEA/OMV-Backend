package com.example.omvbackend.work.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class WorkDTO {

    private long id;
    private Long size;
    private String user;
    private String singleName;
    private String producerName;
    private String artistName;
    private String description;
    private String image;
    private Date releaseDate;
    private String writer;
    private String master;
}


