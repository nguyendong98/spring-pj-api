package com.springboot.training.services;

import com.springboot.training.controllers.request.ForgotPassRequest;
import com.springboot.training.controllers.request.OTPRequest;
import com.springboot.training.dto.mapper.UserMapper;
import com.springboot.training.dto.model.UserDto;
import com.springboot.training.exception.BRSException;
import com.springboot.training.exception.EntityType;
import com.springboot.training.exception.ExceptionType;
import com.springboot.training.models.Role;
import com.springboot.training.models.User;
import com.springboot.training.repo.RoleRepository;
import com.springboot.training.repo.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Stream;

import static com.springboot.training.exception.EntityType.USER;
import static com.springboot.training.exception.ExceptionType.*;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public static final String ACCOUNT_SID = "AC62952f9612e54a1fdcb1c15aa132599f";
    public static final String AUTH_TOKEN = "c2586ce626f3da1a89e59607afee3d9c";
    public static final String SERVICE_SID = "VA15a7790f71697815b7d2dd91bc7996f4";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }

    @Override
    public UserDto signup(UserDto userDto) {
        Role userRole;
        User user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
            if (userDto.isAdmin()) {
                userRole = roleRepository.findByName("ROLE_ADMIN");
            } else {
                userRole = roleRepository.findByName("ROLE_USER");
            }
            user = new User()
                    .setUsername(userDto.getUsername())
                    .setName(userDto.getName())
                    .setPassword(passwordEncoder.encode(userDto.getPassword()))
                    .setRoles(new HashSet<>(List.of(userRole)));
            return UserMapper.toUserDto(userRepository.save(user));
        }
        throw exception(USER, DUPLICATE_ENTITY, userDto.getUsername());
    }

    @Override
    public Stream<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toUserDto);
    }

    @Override
    public String requestOTP(OTPRequest otpRequest) {

        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(otpRequest.getUsername()));
        log.info("User {}", user);
        if (user.isPresent()) {

            User userModel = user.get();
            log.info("user model {}", userModel.getUsername());
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Verification verification = Verification.creator(
                            SERVICE_SID,
                            userModel.getUsername(),
                            "email")
                    .create();

            System.out.println(verification);
            return "Send OTP to email " +userModel.getUsername()+ " successfully";
        }
        throw exception(USER, ENTITY_NOT_FOUND, otpRequest.getUsername());
    }

    @Override
    public String verifyOTPAndChangePass(ForgotPassRequest forgotPassRequest) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(forgotPassRequest.getUsername()));
        if (user.isPresent()) {

            User userModel = user.get();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            VerificationCheck verificationCheck = VerificationCheck.creator(
                            SERVICE_SID,
                            forgotPassRequest.getCode())
                    .setTo(forgotPassRequest.getUsername()).create();

            log.info("check {}", verificationCheck);
            if (verificationCheck.getStatus().equals("approved")) {
                userModel.setPassword(passwordEncoder.encode(forgotPassRequest.getNewPass()));
                return "Change password for user "+userModel.getUsername()+ " successfully!";
            }
            throw exception(USER, ENTITY_EXCEPTION, "Verify OTP fail");
        }
        throw exception(USER, ENTITY_NOT_FOUND, forgotPassRequest.getUsername());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return UserMapper.toUserDto(user);
        }
        throw exception(USER, ENTITY_NOT_FOUND, username);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }






    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return BRSException.throwException(entityType, exceptionType, args);
    }
}
