package com.trackerservice.ProgressPal;

import com.trackerservice.ProgressPal.AppUser.User;
import com.trackerservice.ProgressPal.AppUser.UserRole;
import com.trackerservice.ProgressPal.AppUser.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class ProgressPalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgressPalServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run (UserService userService) {
		return args -> {
			userService.saveRole(new UserRole(null, "ROLE_USER"));
			userService.saveRole(new UserRole(null, "ROLE_MANAGER"));
			userService.saveRole(new UserRole(null, "ROLE_ADMIN"));
			userService.saveRole(new UserRole(null, "ROLE_SUPER_ADMIN"));
			userService.saveUser(new User("John", "Fahnestock", "jf@live.com", "password123", new ArrayList<>()));
			userService.saveUser(new User("will", "Smith", "ws@live.com", "password123", new ArrayList<>()));

			userService.addRoleToUser("jf@live.com", "ROLE_USER");
			userService.addRoleToUser("ws@live.com", "ROLE_ADMIN");
		};
	}

}
