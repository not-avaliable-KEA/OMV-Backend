package com.example.omvbackend.livevideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class LiveVideo {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String title;
    private String intro;
    private LocalDate date;

    public LiveVideo(String url, String titel, String intro, LocalDate date) {
        this.url = url;
        this.title = titel;
        this.intro = intro;
        this.date = date;
    }

    public LiveVideo update(LiveVideo liveVideo) {
        if (liveVideo.url != null && !liveVideo.url.trim().isEmpty())
            this.url = liveVideo.getUrl();

        if (liveVideo.title != null && !liveVideo.title.trim().isEmpty())
            this.title = liveVideo.getTitle();

        if (liveVideo.intro != null && !liveVideo.intro.trim().isEmpty())
            this.intro = liveVideo.getIntro();

        if (liveVideo.date != null)
            this.date = liveVideo.date;
        return this;
    }
}
