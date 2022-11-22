package com.example.omvbackend.user.service;

import com.example.omvbackend.user.model.User;
import com.example.omvbackend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repo;


    //create
     public User create(User user){
         return repo.save(user);
     }

    //Read
    public List<User> getAll(){
         return repo.findAll();
    }

    //update - hej


    //delete
}
