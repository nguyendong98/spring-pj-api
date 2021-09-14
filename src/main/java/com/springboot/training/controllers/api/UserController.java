package com.springboot.training.controllers.api;

import com.springboot.training.controllers.request.UserSignupRequest;
import com.springboot.training.dto.model.UserDto;
import com.springboot.training.dto.response.Response;
import com.springboot.training.models.Role;
import com.springboot.training.models.User;
import com.springboot.training.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>>getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    /**
     * Handles the incoming POST API "/v1/user/signup"
     *
     * @param userSignupRequest
     * @return
     */
    @PostMapping("/register")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        return Response.ok().setPayload(registerUser(userSignupRequest, false));
    }

    /**
     * Register a new user in the database
     *
     * @param userSignupRequest
     * @return
     */
    private UserDto registerUser(UserSignupRequest userSignupRequest, boolean isAdmin) {
        UserDto userDto = new UserDto()
                .setName(userSignupRequest.getName())
                .setPassword(userSignupRequest.getPassword())
                .setUsername(userSignupRequest.getUsername())
                .setAdmin(isAdmin);
        return userService.signup(userDto);
    }

//    @PostMapping("/role/save")
//    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/user/role/save").toUriString());
//        return ResponseEntity.created(uri).body(userService.saveRole(role));
//    }

//    @PostMapping("/role/addtouser")
//    public ResponseEntity<Role>addRoleToUser(@RequestBody RoleToUserForm form) {
//        userService.addRoleToUser(form.getUsername(), form.getRoleName());
//        return ResponseEntity.ok().build();
//    }
}

//@Data
//class RoleToUserForm {
//    private String username;
//    private String roleName;
//}