package com.example.omvbackend.work.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CoverDTO {

    private long id;
    private String image;
    private String artistName;
    private String singleName;
    private String releaseDate;
}


