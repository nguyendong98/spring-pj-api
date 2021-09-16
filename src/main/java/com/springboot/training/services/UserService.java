package com.springboot.training.services;

import com.springboot.training.controllers.request.ForgotPassRequest;
import com.springboot.training.controllers.request.OTPRequest;
import com.springboot.training.dto.model.UserDto;
import com.springboot.training.models.Role;
import com.springboot.training.models.User;
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


    /**
     * Forget password
     *
     * @param otpRequest
     * @return
     */
    String requestOTP(OTPRequest otpRequest);

    /**
     * Verify and change password
     *
     * @param forgotPassRequest
     * @return
     */
    String verifyOTPAndChangePass(ForgotPassRequest forgotPassRequest);


    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
}
