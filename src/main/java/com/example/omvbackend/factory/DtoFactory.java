package com.example.omvbackend.factory;

import com.example.omvbackend.OmvBackendApplication;
import com.example.omvbackend.blogPost.DTOs.BlogPostDTO;
import com.example.omvbackend.blogPost.model.BlogPost;
import com.example.omvbackend.user.DTOs.UserDTO;
import com.example.omvbackend.user.model.User;
import com.example.omvbackend.work.DTOs.WorkDTO;
import com.example.omvbackend.work.model.Work;
import com.example.omvbackend.work.service.WorkService;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory {

    private static final ModelMapper modelMapper = OmvBackendApplication.modelMapper();
    private static final DateTimeFormatter formatterWork = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    //user

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

    //work

    public static WorkService workService;

    public static void setWorkService(WorkService workService){
        DtoFactory.workService = workService;
    }

    //takes WORk parameter, returns workDTO and parse
    public static WorkDTO fromWork(Work work) {
        WorkDTO workDTO = new WorkDTO();

        if (work.getReleaseDate() != null) {
            String dateTime = work.getReleaseDate().format(formatterWork);
            workDTO.setReleaseDate(dateTime);
        }

        workDTO.setReleaseName(work.getReleaseName());
        workDTO.setArtist(work.getArtist());
        workDTO.setCredit(work.getCredit());
        workDTO.setCommentary(work.getCommentary());
        workDTO.setImage(work.getImage());
        workDTO.setId(work.getId());

        return workDTO;
    }

    public static List<WorkDTO> fromWorks(List<Work> works){
        return works.stream()
                .sorted(Comparator.comparing(Work::getReleaseDate).reversed())
                .map(DtoFactory::fromWork)
                .collect(Collectors.toList());
    }

    public static Work fromWorkDTO(WorkDTO workDTO) {
        Work work = new Work();

        //we parse workDTOs releaseDato to Localedate
        if(workDTO.getReleaseDate() != null && !workDTO.getReleaseDate().isEmpty()){
            System.out.println(workDTO.getReleaseDate());
            LocalDate dateTime = LocalDate.parse(workDTO.getReleaseDate(), formatterWork);
            work.setReleaseDate(dateTime);
        } else {
            work.setReleaseDate(LocalDate.now());
        }

        work.setArtist(workDTO.getArtist());
        work.setReleaseName(workDTO.getReleaseName());
        work.setCredit(workDTO.getCredit());
        work.setCommentary(workDTO.getCommentary());
        work.setImage(workDTO.getImage());
        work.setId(workDTO.getId());

        return work;
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


