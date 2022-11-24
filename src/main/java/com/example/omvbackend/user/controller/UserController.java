package com.example.omvbackend.user.controller;

import com.example.omvbackend.user.model.User;
import com.example.omvbackend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }
    //create
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        return ResponseEntity.ok().body(service.create(user));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    // get 1
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable long id) {
        Optional<User> user = service.get(id);

        if (user.isEmpty()) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(user.get());
    }

    // update
    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @Valid @RequestBody User user) {
        user.setId(id);

        User updatedUser = service.update(user);

        if (updatedUser == null) {
            return ResponseEntity.badRequest().body("Bad id");
        }

        return ResponseEntity.ok().body(user);
    }
}
