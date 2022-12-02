package com.example.omvbackend.factory;

import com.example.omvbackend.OmvBackendApplication;
import com.example.omvbackend.user.DTOs.UserDTO;
import com.example.omvbackend.user.model.User;
import com.example.omvbackend.work.DTOs.WorkDTO;
import com.example.omvbackend.work.model.Work;
import com.example.omvbackend.work.service.WorkService;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory {

    private static ModelMapper modelMapper = OmvBackendApplication.modelMapper();
    private static final DateTimeFormatter formatterWork = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    //user

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

        workDTO.setSingleName(work.getSingleName());
        workDTO.setArtistName(work.getArtistName());
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

        //we parse workDTOs releaseDato to Locatedate
        if(workDTO.getReleaseDate() != null && !workDTO.getReleaseDate().isEmpty()){
            System.out.println(workDTO.getReleaseDate());
            LocalDate dateTime = LocalDate.parse(workDTO.getReleaseDate(), formatterWork);
            work.setReleaseDate(dateTime);
        } else {
            work.setReleaseDate(LocalDate.now());
        }

        work.setArtistName(workDTO.getArtistName());
        work.setSingleName(workDTO.getSingleName());
        work.setImage(workDTO.getImage());
        work.setId(workDTO.getId());

        return work;
    }

}


