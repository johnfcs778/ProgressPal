package com.trackerservice.ProgressPal;

import com.trackerservice.ProgressPal.Movement.Movement;
import com.trackerservice.ProgressPal.Movement.MovementRepository;
import com.trackerservice.ProgressPal.Workout.Workout;
import com.trackerservice.ProgressPal.Workout.WorkoutRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class ProgressPalServiceApplicationTests {

	@Autowired
	WorkoutRepository workoutRepository;

	@Autowired
	MovementRepository movementRepository;

	@AfterEach
	void tearDown() {
		workoutRepository.deleteAll();
		movementRepository.deleteAll();
		movementRepository.flush();
	}

	// Workout tests

	@Test
	void insertWorkout() {
		Workout workout = new Workout(
			"Push",
			LocalDate.now(),
			50,
			"workout",
			true
		);

		workoutRepository.save(workout);

		assertThat(workoutRepository.findWorkoutById(1)).isPresent();

	}

	@Test
	void getWorkouts() {
		Workout workout = new Workout(
				"Push",
				LocalDate.now(),
				50,
				"workout",
				true
		);

		Workout workout2 = new Workout(
				"Push",
				LocalDate.now(),
				50,
				"workout",
				true
		);

		workoutRepository.save(workout);
		workoutRepository.save(workout2);

		assertThat(workoutRepository.findAll().size()).isEqualTo(2);

	}

	@Test
	void findWorkoutByDate() {
		Workout workout = new Workout(
				"Push",
				LocalDate.now(),
				50,
				"workout",
				true
		);

		workoutRepository.save(workout);

		assertThat(workoutRepository.findWorkoutByDate(LocalDate.now())).isPresent();

	}

	// Movement Tests

	@Test
	void insertMovement() {
		Movement movement = new Movement(
				"test",
				5,
				200,
				300,
				315
		);

		movementRepository.save(movement);

		assertThat(movementRepository.findById(4)).isPresent();

	}

	@Test
	void getMovements() {
		Movement movement = new Movement(
				"test",
				5,
				200,
				300,
				315
		);

		Movement  movement2 = new Movement(
				"test1",
				5,
				200,
				300,
				315
		);

		movementRepository.save(movement);
		movementRepository.save(movement2);

		assertThat(movementRepository.findAll().size()).isEqualTo(2);

	}

	@Test
	void findMovementByName() {
		Movement movement = new Movement(
				"test",
				5,
				200,
				300,
				315
		);

		movementRepository.save(movement);

		assertThat(movementRepository.findMovementByName("test")).isPresent();

	}

}
