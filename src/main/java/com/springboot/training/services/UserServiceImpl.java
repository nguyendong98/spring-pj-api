package com.springboot.training.services;

import com.springboot.training.dto.mapper.UserMapper;
import com.springboot.training.dto.model.UserDto;
//import com.springboot.training.exception.BRSException;
import com.springboot.training.exception.BRSException;
import com.springboot.training.exception.EntityType;
import com.springboot.training.exception.ExceptionType;
import com.springboot.training.models.Role;
import com.springboot.training.models.User;
import com.springboot.training.models.UserRoles;
import com.springboot.training.repo.RoleRepository;
import com.springboot.training.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Stream;

import static com.springboot.training.exception.EntityType.USER;
import static com.springboot.training.exception.ExceptionType.DUPLICATE_ENTITY;


@Service @RequiredArgsConstructor @Transactional @Slf4j
@Component
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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
        log.info("role {}", authorities);
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

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
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
