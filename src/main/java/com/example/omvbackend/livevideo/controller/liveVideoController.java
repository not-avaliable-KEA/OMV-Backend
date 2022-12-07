package com.example.omvbackend.livevideo.controller;
import com.example.omvbackend.factory.DtoFactory;
import com.example.omvbackend.livevideo.DTOs.LiveVideoDTO;
import com.example.omvbackend.livevideo.model.LiveVideo;
import com.example.omvbackend.livevideo.service.LiveVideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "https://white-sand-06fa66003.2.azurestaticapps.net/"}, allowCredentials = "true")
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

    //get -
    @GetMapping("/{id}")
    public ResponseEntity<LiveVideoDTO> get(@PathVariable("id") Long id) {
        Optional<LiveVideo> item = service.get(id);
        if (item.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok().body(DtoFactory.fromLiveVideo(item.get()));
    }

    //update

    @PatchMapping("/{id}")
    public ResponseEntity<LiveVideo> update(HttpSession session,
                                           @Valid @RequestBody LiveVideoDTO liveVideoDTO,
                                           @PathVariable long id) {
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);

        LiveVideo liveVideo = DtoFactory.fromLiveVideoDTO(liveVideoDTO);

        liveVideo.setId(id);

        Optional<LiveVideo> result = service.update(liveVideo);

        if (result.isPresent()) {
            return ResponseEntity.ok().body(result.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(HttpSession session, @PathVariable long id) {
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(service.delete(id));
    }


}


