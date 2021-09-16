package com.springboot.training.controllers.api;

import com.springboot.training.controllers.request.ForgotPassRequest;
import com.springboot.training.controllers.request.OTPRequest;
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

    @PostMapping("/forgotpass/request")
    public Response requestOTP(@RequestBody OTPRequest otpRequest) {
        return Response.ok().setPayload(userService.requestOTP(otpRequest));
    }

    @PostMapping("/forgotpass/verify")
    public Response verifyOTPAndChangePass(@RequestBody ForgotPassRequest forgotPassRequest) {
        return Response.ok().setPayload(userService.verifyOTPAndChangePass(forgotPassRequest));
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




}

