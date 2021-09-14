package com.springboot.training.services;

import com.springboot.training.dto.model.UserDto;
import com.springboot.training.models.Role;
import com.springboot.training.models.User;
import com.springboot.training.models.UserRoles;

import java.util.List;

public interface UserService {
    /**
     * Register a new user
     *
     * @param userDto
     * @return
     */
    UserDto signup(UserDto userDto);





    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
