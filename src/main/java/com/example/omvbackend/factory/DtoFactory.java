package com.example.omvbackend.factory;

import com.example.omvbackend.OmvBackendApplication;
import com.example.omvbackend.user.DTOs.UserDTO;
import com.example.omvbackend.user.model.User;
import com.example.omvbackend.work.DTOs.WorkDTO;
import com.example.omvbackend.work.model.Work;
import com.example.omvbackend.work.service.WorkService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DtoFactory
{
    private static ModelMapper modelMapper = OmvBackendApplication.modelMapper();

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

    public static WorkService workService;

    public static void setWorkService(WorkService workService){
        DtoFactory.workService = workService;
    }

    public static WorkDTO fromWork(Work work)
    {
        return modelMapper.map(work, WorkDTO.class);
    }

    public static List<WorkDTO> fromWorks(List<Work> works){
        return works.stream().map(work -> modelMapper.map(work, WorkDTO.class))
                .collect(Collectors.toList());
    }

    public static Work fromWorkDTO(WorkDTO workDTO) {
        return modelMapper.map(workDTO, Work.class);
    }

}


