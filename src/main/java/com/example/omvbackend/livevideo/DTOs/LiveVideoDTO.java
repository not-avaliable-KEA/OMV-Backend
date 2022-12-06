package com.example.omvbackend.livevideo.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class LiveVideoDTO {

    private Long id;
    private String url;
    private String title;
    private String intro;
    private LocalDate date;
}
