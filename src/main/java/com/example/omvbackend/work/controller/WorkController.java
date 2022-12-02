package com.example.omvbackend.work.controller;

import com.example.omvbackend.factory.DtoFactory;
import com.example.omvbackend.work.DTOs.WorkDTO;
import com.example.omvbackend.work.model.Work;
import com.example.omvbackend.work.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = {"http://127.0.0.1:5500",  "https://white-sand-06fa66003.2.azurestaticapps.net/"}, allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/work")

public class WorkController {
    private final WorkService service;

public WorkController(WorkService service){
    this.service = service;

}

    @PostMapping
    public ResponseEntity<WorkDTO> create(HttpSession session, @Valid @RequestBody WorkDTO work){
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);
        Work item = service.create(DtoFactory.fromWorkDTO(work));
        return ResponseEntity.ok().body(DtoFactory.fromWork(item));
    }

    @GetMapping
    public ResponseEntity<List<WorkDTO>> findAll(){
        return ResponseEntity.ok().body(DtoFactory.fromWorks(service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDTO> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Work> item = service.get(id);

        if (item.isEmpty()) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok().body(DtoFactory.fromWork(item.get()));
    }



    @PatchMapping("/{id}")
    public ResponseEntity<WorkDTO> update(HttpSession session, @Valid @RequestBody WorkDTO workDTO, @PathVariable("id") Long id){
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);
        workDTO.setId(id);
        Work item = service.update(DtoFactory.fromWorkDTO(workDTO));
        return ResponseEntity.ok().body(DtoFactory.fromWork(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(HttpSession session, @PathVariable("id") Long id) {
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);
        service.get(id).orElseThrow( () -> new ResourceNotFoundException("Work %d not found.".formatted(id)));

        boolean delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

}
