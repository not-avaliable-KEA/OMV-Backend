package com.example.omvbackend.factory;

import com.example.omvbackend.OmvBackendApplication;
import com.example.omvbackend.blogPost.DTOs.BlogPostDTO;
import com.example.omvbackend.blogPost.model.BlogPost;
import com.example.omvbackend.user.DTOs.UserDTO;
import com.example.omvbackend.user.model.User;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory
{
    private static ModelMapper modelMapper = OmvBackendApplication.modelMapper();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /********
     * User
     ********/
    public static UserDTO fromUser(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public static User fromUserDto(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public static List<UserDTO> fromUsers(List<User> users) {
        return users.stream()
                .map(DtoFactory::fromUser)
                .collect(Collectors.toList());
    }


    /***********
     * BlogPost
     ***********/

    public static BlogPostDTO fromBlogPost(BlogPost blogPost) {
        BlogPostDTO dto = modelMapper.map(blogPost, BlogPostDTO.class);

        LocalDateTime timeUnformated = LocalDateTime.parse(dto.getCreatedDate());

        dto.setCreatedDate(timeUnformated.format(formatter));

        return dto;
    }

    public static BlogPost fromBlogPostDTO(BlogPostDTO blogPostDTO) {
        // create new empty blogpost
        BlogPost blogPost = new BlogPost();

        // parse and set createDate
        if (blogPostDTO.getCreatedDate() != null && !blogPostDTO.getCreatedDate().trim().isEmpty()) {
            LocalDateTime dateTime = LocalDateTime.parse(blogPostDTO.getCreatedDate(), formatter);
            blogPost.setCreatedDate(dateTime);
        }

        // set the rest of the values
        blogPost.setId(blogPostDTO.getId());
        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setText(blogPostDTO.getText());
        blogPost.setPicture(blogPostDTO.getPicture());

        //return blogPost.
        return blogPost;
    }

    public static List<BlogPostDTO> fromBlogPosts(List<BlogPost> blogPosts) {
        return blogPosts.stream()
                .map(DtoFactory::fromBlogPost)
                .collect(Collectors.toList());
    }

}
