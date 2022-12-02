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
    private String artistName;
    private String singleName;
    private String releaseDate;
}


