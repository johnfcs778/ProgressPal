package com.trackerservice.ProgressPal;

import com.trackerservice.ProgressPal.AppUser.User;
import com.trackerservice.ProgressPal.AppUser.UserRole;
import com.trackerservice.ProgressPal.AppUser.UserService;
import com.trackerservice.ProgressPal.Movement.Movement;
import com.trackerservice.ProgressPal.Movement.MovementService;
import com.trackerservice.ProgressPal.Workout.Workout;
import com.trackerservice.ProgressPal.Workout.WorkoutService;
import org.hibernate.jdbc.Work;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class ProgressPalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgressPalServiceApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run (UserService userService, MovementService movementService, WorkoutService mWorkoutService) {
//		return args -> {
//			userService.saveRole(new UserRole(null, "ROLE_USER"));
//			userService.saveRole(new UserRole(null, "ROLE_MANAGER"));
//			userService.saveRole(new UserRole(null, "ROLE_ADMIN"));
//			userService.saveRole(new UserRole(null, "ROLE_SUPER_ADMIN"));
//			userService.saveUser(new User("John", "Fahnestock", "jf@live.com", "password123", new ArrayList<>()));
//			userService.saveUser(new User("will", "Smith", "ws@live.com", "password123", new ArrayList<>()));
//
//			userService.addRoleToUser("jf@live.com", "ROLE_USER");
//			userService.addRoleToUser("ws@live.com", "ROLE_ADMIN");
//
//			mWorkoutService.addNewWorkout(new Workout("sdds1", LocalDate.now(), 1, "sdf", true, 1));
//			mWorkoutService.addNewWorkout(new Workout("sdds2", LocalDate.now(), 1, "sdf", true, 1));
//			mWorkoutService.addNewWorkout(new Workout("sdds3", LocalDate.now(), 1, "sdf", true, 1));
//			mWorkoutService.addNewWorkout(new Workout("sdds4", LocalDate.now(), 1, "sdf", true, 2));
//			mWorkoutService.addNewWorkout(new Workout("sdds5", LocalDate.now(), 1, "sdf", true, 2));
//			mWorkoutService.addNewWorkout(new Workout("sdds6", LocalDate.now(), 1, "sdf", true, 2));
//			movementService.addNewMovement(new Movement("Fun", 2, 2, 2, 2, 1));
//			movementService.addNewMovement(new Movement("Fun1", 2, 2, 2, 2, 1));
//			movementService.addNewMovement(new Movement("Fun2", 2, 2, 2, 2, 1));
//
//			movementService.addNewMovement(new Movement("Fun3", 2, 2, 2, 2, 2));
//			movementService.addNewMovement(new Movement("Fun4", 2, 2, 2, 2, 2));
//			movementService.addNewMovement(new Movement("Fun5", 2, 2, 2, 2, 2));
//		};
//	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}

}
