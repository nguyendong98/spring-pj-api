package com.springboot.training.dto.mapper;

import com.springboot.training.dto.model.RoleDto;
import com.springboot.training.dto.model.UserDto;
import com.springboot.training.models.User;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;


@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setUsername(user.getUsername())
                .setName(user.getName())
                .setRoles(new HashSet<RoleDto>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, RoleDto.class))
                        .collect(Collectors.toSet())));
    }

}
