package com.example.omvbackend.blogPost.model;

import lombok.AccessLevel;
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

    private String Text;

    private String picture;

    public BlogPost(LocalDateTime createdDate, String text, String picture) {
        this.createdDate = createdDate;
        Text = text;
        this.picture = picture;
    }
}
