package com.example.omvbackend.work.controller;

import com.example.omvbackend.factory.DtoFactory;
import com.example.omvbackend.work.DTOs.CoverListDTO;
import com.example.omvbackend.work.model.Work;
import com.example.omvbackend.work.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/work")

public class WorkController {
    private final WorkService service;


public WorkController(WorkService service){
    this.service = service;

}

    @GetMapping()
    public ResponseEntity<List<CoverListDTO>> findAll(){
        return ResponseEntity.ok().body(DtoFactory.fromWorks(service.getAll()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CoverListDTO> find(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Work> item = service.get(id);

        if (item.isEmpty()) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok().body(DtoFactory.fromWork(item.get()));
    }

    @PostMapping()
    public ResponseEntity<CoverListDTO> create(@RequestBody Work work){

        Work item = service.create(work);
        return ResponseEntity.ok().body(DtoFactory.fromWork(item));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<CoverListDTO> update(@Valid @RequestBody CoverListDTO workDTO, @PathVariable("id") Long id){
        workDTO.setId(id);
        Work item = service.update(DtoFactory.fromWorkDTO(workDTO));
        return ResponseEntity.ok().body(DtoFactory.fromWork(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        service.get(id).orElseThrow( () -> new ResourceNotFoundException("Work %d not found.".formatted(id)));

        boolean delete = service.delete(id);
        return ResponseEntity.ok().body(delete);
    }

}
