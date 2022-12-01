package com.example.omvbackend;

import com.example.omvbackend.user.model.User;
import com.example.omvbackend.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OmvBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmvBackendApplication.class, args);
    }

    @Bean
    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * CommandLineRunner to add the adminUser
     */
    @Bean
    public CommandLineRunner importData(UserService service) {
        return (args) -> {
        service.create(new User("adminUser", "adminUserPasseord"));
        };
    }

}
