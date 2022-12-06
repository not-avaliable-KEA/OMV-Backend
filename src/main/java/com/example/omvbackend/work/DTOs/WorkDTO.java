package com.example.omvbackend.work.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkDTO {

    private long id;
    private String image;
    private String artist;
    private String release;
    private String releaseDate;
    private String commentary;
    private String credit;
}


