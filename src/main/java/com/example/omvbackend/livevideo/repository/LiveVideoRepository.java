package com.example.omvbackend.livevideo.repository;

import com.example.omvbackend.livevideo.model.LiveVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveVideoRepository extends JpaRepository<LiveVideo, Long> {
}
