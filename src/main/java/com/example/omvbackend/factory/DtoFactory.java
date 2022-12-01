package com.example.omvbackend.factory;

import com.example.omvbackend.OmvBackendApplication;
import com.example.omvbackend.user.DTOs.UserDTO;
import com.example.omvbackend.user.model.User;
import com.example.omvbackend.work.DTOs.CoverDTO;
import com.example.omvbackend.work.model.Work;
import com.example.omvbackend.work.service.WorkService;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static CoverDTO fromWork(Work work) {
        CoverDTO coverDTO = new CoverDTO();


        String dateTime = work.getReleaseDate().format(formatterWork);
        coverDTO.setReleaseDate(dateTime);

        return coverDTO;
    }

    public static List<CoverDTO> fromWorks(List<Work> works){
        return works.stream()
                .sorted(Comparator.comparing(Work::getReleaseDate))
                .map(work -> modelMapper.map(work, CoverDTO.class))
                .collect(Collectors.toList());
    }

    public static Work fromWorkDTO(CoverDTO workDTO) {
        return modelMapper.map(workDTO, Work.class);
    }

}


