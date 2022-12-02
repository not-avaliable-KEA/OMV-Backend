package com.example.omvbackend.work.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String singleName;
    private String producerName;
    private String artistName;
    private String description;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String image;
    private LocalDate releaseDate;
    private String writer;
    private String master;

    public Work(String singleName, String producerName, String artistName, String description, String image, LocalDate releaseDate, String writer, String master) {
        this.singleName = singleName;
        this.producerName = producerName;
        this.artistName = artistName;
        this.description = description;
        this.image = image;
        this.releaseDate = releaseDate;
        this.writer = writer;
        this.master = master;
    }

    public Work update(Work work) {
        this.singleName = work.getSingleName();
        this.producerName = work.getProducerName();
        this.artistName = work.getArtistName();
        this.description = work.getDescription();
        this.image = work.getImage();
        this.releaseDate = work.releaseDate;
        this.writer = work.getWriter();
        this.master = work.getMaster();
        return this;
    }

}




