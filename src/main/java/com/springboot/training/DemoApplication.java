package com.springboot.training;

import com.springboot.training.dto.model.UserDto;
import com.springboot.training.models.Role;
import com.springboot.training.models.User;
import com.springboot.training.models.UserRoles;
import com.springboot.training.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashSet;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//			userService.saveRole(new Role(null, "ROLE_USER"));
//
//			userService.signup(
//					new UserDto(null, "admin", "admin@gmail.com", "123456", true, new HashSet<>())
//			);
////			userService.saveUser(
////					new User(null, "Nguyen Nam", "ngnam2001", "123456", new HashSet<>())
////			);
//
////			userService.addRoleToUser("ngdong98", "ROLE_ADMIN");
//
//
//		};
//	}



}
