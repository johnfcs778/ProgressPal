package com.trackerservice.ProgressPal.Workout;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutServiceTest {

    @Mock
    private WorkoutRepository mWorkoutRepository;
    private WorkoutService mTestService;

    @BeforeEach
    void setUp() {
        mTestService = new WorkoutService(mWorkoutRepository);
    }

    @AfterEach
    void tearDown() {
        mWorkoutRepository.deleteAll();
    }

    @Test
    void getAllWorkouts() {
        mTestService.getWorkouts();
        verify(mWorkoutRepository).findAll();
    }

    @Test
    void addNewWorkout() {
        Workout workout = new Workout("Push", LocalDate.now(),40.0,"fun",true, 1);
        mTestService.addNewWorkout(workout);
        ArgumentCaptor<Workout> argumentCaptor = ArgumentCaptor.forClass(Workout.class);
        verify(mWorkoutRepository).save(argumentCaptor.capture());
        Workout capturedWorkout = argumentCaptor.getValue();
        assertThat(capturedWorkout).isEqualTo(workout);
    }

    @Test
    void addNewWorkoutInvalid() {
        Workout workout = new Workout("Push", LocalDate.now().plusDays(1),40.0,"fun",true, 1);
        assertThatThrownBy(()-> mTestService.addNewWorkout(workout)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deleteWorkoutInvalid() {
        // Since we are mocking the repo there are no ids in the db
        assertThatThrownBy(()-> mTestService.deleteWorkout(1)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void updateWorkout() {
        Workout mockWorkout = mock(Workout.class);
        when(mockWorkout.getWorkoutType()).thenReturn("mock");
        when(mockWorkout.getNotes()).thenReturn("mock");
        when(mWorkoutRepository.findById(any()))
                .thenReturn(java.util.Optional.of(mockWorkout));
        mTestService.updateWorkout(1,"newType","newNotes");
        verify(mockWorkout).setWorkoutType("newType");
        verify(mockWorkout).setNotes("newNotes");
    }
}