package com.springboot.training.services;

import com.springboot.training.dto.model.UserDto;
import com.springboot.training.models.Role;
import com.springboot.training.models.User;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public interface UserService {
    /**
     * Register a new user
     *
     * @param userDto
     * @return
     */
    UserDto signup(UserDto userDto);

    Stream<UserDto> getAllUser();





    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
}
