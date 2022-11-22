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
    //create controller
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        return ResponseEntity.ok().body(service.create(user));
    }

    //show all controller
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }



}
