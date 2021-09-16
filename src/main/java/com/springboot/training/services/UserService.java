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


    /**
     * Get all  user
     *
     * @return
     */
    Stream<UserDto> getAllUser();


    /**
     * Request OTP
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

    /**
     * Verify and change password
     *
     * @param username
     * @return
     */
    UserDto getUserByUsername(String username);

    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
}
