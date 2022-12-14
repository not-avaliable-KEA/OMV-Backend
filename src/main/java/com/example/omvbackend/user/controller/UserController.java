package com.example.omvbackend.user.controller;

import com.example.omvbackend.factory.DtoFactory;
import com.example.omvbackend.user.DTOs.UserDTO;
import com.example.omvbackend.user.model.User;
import com.example.omvbackend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "https://white-sand-06fa66003.2.azurestaticapps.net/"}, allowCredentials = "true")
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;


    public UserController(UserService service) {
        this.service = service;
    }
    //create
    @PostMapping
    public ResponseEntity<User> create(HttpSession session, @Valid @RequestBody User user){
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(service.create(user));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(HttpSession session){
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(DtoFactory.fromUsers(service.getAll()));
    }

    // get 1
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(HttpSession session, @PathVariable long id) {
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);
        Optional<User> user = service.get(id);

        if (user.isEmpty()) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(DtoFactory.fromUser(user.get()));
    }

    // update
    @PatchMapping("/{id}")
    public ResponseEntity update(HttpSession session, @PathVariable long id, @Valid @RequestBody User user) {
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);

        user.setId(id);

        User updatedUser = service.update(user);

        if (updatedUser == null) {
            return ResponseEntity.badRequest().body("Bad id");
        }

        return ResponseEntity.ok().body(DtoFactory.fromUser(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(HttpSession session, @PathVariable long id) {
        if (session.getAttribute("user") == null) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok().body(service.delete(id));
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(HttpSession session, @Valid @RequestBody User user){
        User loginUser = service.checkLogin(user);

        if (loginUser == null) return ResponseEntity.ok().body(null);

        session.setAttribute("user", loginUser);
        return ResponseEntity.ok().body(DtoFactory.fromUser(loginUser));
    }

    // logout
    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok().body("Logout complete");
    }
}
