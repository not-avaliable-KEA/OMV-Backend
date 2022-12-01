package com.example.omvbackend.blogPost.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime createdDate;

    @Column(columnDefinition="MEDIUMTEXT")
    private String text;

    @Column(columnDefinition="MEDIUMTEXT")
    private String picture;
    private String title;

    public BlogPost(LocalDateTime createdDate, String title, String text, String picture) {
        this.createdDate = createdDate;
        this.title = title;
        this.text = text;
        this.picture = picture;
    }
}
