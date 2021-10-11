package com.springboot.training.controllers.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springboot.training.controllers.request.ForgotPassRequest;
import com.springboot.training.controllers.request.OTPRequest;
import com.springboot.training.controllers.request.UserSignupRequest;
import com.springboot.training.dto.model.UserDto;
import com.springboot.training.dto.response.Response;
import com.springboot.training.services.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequestMapping(path = "api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping(path = "/register")
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
    public Response getAllUser() {
        return Response
                .ok()
                .setPayload(userService.getAllUser());
    }

    @GetMapping("/info")
    public Response getUserInfo(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        return Response
                .ok()
                .setPayload(userService.getUserByUsername(username));
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

