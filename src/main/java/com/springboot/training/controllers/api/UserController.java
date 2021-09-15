package com.springboot.training.controllers.api;

import com.springboot.training.controllers.request.UserSignupRequest;
import com.springboot.training.dto.model.UserDto;
import com.springboot.training.dto.response.Response;
import com.springboot.training.models.User;
import com.springboot.training.services.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public Response signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {
        return Response.ok().setPayload(registerUser(userSignupRequest, false));
    }

    @GetMapping()
    public Response get() {
        return Response
                .ok()
                .setPayload(userService.getAllUser());
    }

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