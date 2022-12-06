package com.example.omvbackend.work.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String release;
    private String credit;
    private String artist;
    private String commentary;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String image;
    private LocalDate releaseDate;

    public Work(String release, String credit, String artist, String commentary, String image, LocalDate releaseDate) {
        this.release = release;
        this.credit = credit;
        this.artist = artist;
        this.commentary = commentary;
        this.image = image;
        this.releaseDate = releaseDate;
    }

    public Work update(Work work) {
        if (work.release != null && !work.release.trim().isEmpty())
            this.release = work.getRelease();

        if (work.credit != null && !work.credit.trim().isEmpty())
            this.credit = work.getCredit();

        if (work.artist != null && !work.artist.trim().isEmpty())
            this.artist = work.getArtist();

        if (work.commentary != null && !work.commentary.trim().isEmpty())
            this.commentary = work.getCommentary();
        if (work.image!= null && !work.image.trim().isEmpty())
            this.image = work.getImage();

        if (work.releaseDate != null)
            this.releaseDate = work.releaseDate;

        return this;
    }

}




