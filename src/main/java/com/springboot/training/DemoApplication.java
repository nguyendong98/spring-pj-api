package com.springboot.training;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



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
