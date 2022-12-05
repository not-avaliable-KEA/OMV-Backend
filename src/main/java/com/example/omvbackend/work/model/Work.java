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
    private String producerName;
    private String artistName;
    private String description;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String image;
    private LocalDate releaseDate;
    private String writer;
    private String master;

    public Work(String singleName, String producerName, String artistName, String description, String image, LocalDate releaseDate, String writer, String master) {
        this.release = singleName;
        this.producerName = producerName;
        this.artistName = artistName;
        this.description = description;
        this.image = image;
        this.releaseDate = releaseDate;
        this.writer = writer;
        this.master = master;
    }

    public Work update(Work work) {
        if (work.release != null && !work.release.trim().isEmpty())
            this.release = work.getRelease();

        if (work.producerName != null && !work.producerName.trim().isEmpty())
            this.producerName = work.getProducerName();

        if (work.artistName != null && !work.artistName.trim().isEmpty())
            this.artistName = work.getArtistName();

        if (work.description != null && !work.description.trim().isEmpty())
            this.description = work.getDescription();
        if (work.image!= null && !work.image.trim().isEmpty())
            this.image = work.getImage();

        if (work.releaseDate != null)
            this.releaseDate = work.releaseDate;

        if (work.writer != null && !work.writer.trim().isEmpty())
            this.writer = work.getWriter();

        if (work.master != null && !work.master.trim().isEmpty())this.master = work.getMaster();
        return this;
    }

}




