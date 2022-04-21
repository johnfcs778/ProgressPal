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

	@Bean
	CommandLineRunner run (UserService userService, MovementService movementService, WorkoutService mWorkoutService) {
		return args -> {
			userService.saveRole(new UserRole(null, "ROLE_USER"));
			userService.saveRole(new UserRole(null, "ROLE_MANAGER"));
			userService.saveRole(new UserRole(null, "ROLE_ADMIN"));
			userService.saveRole(new UserRole(null, "ROLE_SUPER_ADMIN"));
			userService.saveUser(new User("John", "Fahnestock", "jf@live.com", "password123", new ArrayList<>()));
			userService.saveUser(new User("will", "Smith", "ws@live.com", "password123", new ArrayList<>()));

			userService.addRoleToUser("jf@live.com", "ROLE_USER");
			userService.addRoleToUser("ws@live.com", "ROLE_ADMIN");

			mWorkoutService.addNewWorkout(new Workout("sdds1", LocalDate.now(), 1, "sdf", true, 1));
			mWorkoutService.addNewWorkout(new Workout("sdds2", LocalDate.now(), 1, "sdf", true, 1));
			mWorkoutService.addNewWorkout(new Workout("sdds3", LocalDate.now(), 1, "sdf", true, 1));
			mWorkoutService.addNewWorkout(new Workout("sdds4", LocalDate.now(), 1, "sdf", true, 2));
			mWorkoutService.addNewWorkout(new Workout("sdds5", LocalDate.now(), 1, "sdf", true, 2));
			mWorkoutService.addNewWorkout(new Workout("sdds6", LocalDate.now(), 1, "sdf", true, 2));
			mWorkoutService.addNewWorkout(new Workout("sdds7", LocalDate.of(2022,4,18), 1, "sdf", true, 1));
			mWorkoutService.addNewWorkout(new Workout("sdds8", LocalDate.now(), 1, "sdf", true, 1));
			mWorkoutService.addNewWorkout(new Workout("sdds9", LocalDate.now(), 1, "sdf", true, 1));
			mWorkoutService.addNewWorkout(new Workout("sdds10", LocalDate.now(), 1, "sdf", true, 1));
			mWorkoutService.addNewWorkout(new Workout("sdds11", LocalDate.now(), 1, "sdf", true, 1));

			movementService.addNewMovementForUser(new Movement("Fun", 2, 2, 2, 2, 1), 1);
			movementService.addNewMovementForUser(new Movement("Fun1", 2, 2, 2, 2, 1), 1);
			movementService.addNewMovementForUser(new Movement("Fun2", 2, 2, 2, 2, 1), 1);

			movementService.addNewMovementForUser(new Movement("Fun", 2, 2, 2, 2, 2), 2);
			movementService.addNewMovementForUser(new Movement("Fun1", 2, 2, 2, 2, 2), 2);
			movementService.addNewMovementForUser(new Movement("Fun2", 2, 2, 2, 2, 2), 2);
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Updated for production: https://progress-pal-front.herokuapp.com
				// Local: http://localhost:3000
				registry.addMapping("/**").allowedOrigins("https://progress-pal-front.herokuapp.com");
				//registry.addMapping("/**").allowedOrigins("http://progress-pal-front.herokuapp.com");
				//registry.addMapping("/**").allowedOrigins("http://localhost:3000");
				//registry.addMapping("/**").allowedOrigins("http://www.progresspal.xyz");
			}
		};
	}

}
