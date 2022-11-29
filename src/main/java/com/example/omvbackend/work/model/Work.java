package com.example.omvbackend.work.model;
import lombok.Data;
import java.util.Date;

import javax.persistence.*;

@Data
public class Work
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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

    public Work(long id, Long size, String user, String singleName, String producerName, String artistName, String description, String image, Date releaseDate, String writer, String master)
    {
        this.id = id;
        this.size = size;
        this.user = user;
        this.singleName = singleName;
        this.producerName = producerName;
        this.artistName = artistName;
        this.description = description;
        this.image = image;
        this.releaseDate = releaseDate;
        this.writer = writer;
        this.master = master;
    }
    public Work update(Work work)
    {
        this.id = id;
        this.size = size;
        this.user = user;
        this.singleName = singleName;
        this.producerName = producerName;
        this.artistName = artistName;
        this.description = description;
        this.image = image;
        this.releaseDate = releaseDate;
        this.writer = writer;
        this.master = master;
        return this;
    }

}



