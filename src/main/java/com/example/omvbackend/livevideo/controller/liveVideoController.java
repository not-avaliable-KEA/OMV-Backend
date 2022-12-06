package com.example.omvbackend.livevideo.controller;

import com.example.omvbackend.blogPost.DTOs.BlogPostDTO;
import com.example.omvbackend.factory.DtoFactory;
import com.example.omvbackend.livevideo.DTOs.LiveVideoDTO;
import com.example.omvbackend.livevideo.model.LiveVideo;
import com.example.omvbackend.livevideo.service.LiveVideoService;
import com.example.omvbackend.work.DTOs.WorkDTO;
import com.example.omvbackend.work.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/liveVideo")
public class liveVideoController {

    private final LiveVideoService service;

    public liveVideoController(LiveVideoService service) {
        this.service = service;
    }

    //create
    @PostMapping
    public ResponseEntity<LiveVideoDTO> create(HttpSession session, @Valid @RequestBody LiveVideoDTO blogPost){
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body
                (DtoFactory.fromLiveVideo(service.create(DtoFactory.fromLiveVideoDTO(blogPost))));
    }

    //getAll
    @GetMapping
    public ResponseEntity<List<LiveVideoDTO>> getAll() {
        return ResponseEntity.ok().body(DtoFactory.fromLiveVideos(service.getAll()));
    }

    //get

}

