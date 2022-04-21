package com.trackerservice.ProgressPal.Movement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovementServiceTest {

    @Mock
    private MovementRepository mMovementRepository;
    private MovementService mTestService;

    @BeforeEach
    void setUp() {
        mTestService = new MovementService(mMovementRepository);
    }

    @AfterEach
    void tearDown() {
        mMovementRepository.deleteAll();
    }

    @Test
    void getMovements() {
        mTestService.getMovements();
        verify(mMovementRepository).findAll();
    }

    @Test
    void addNewMovement() {
        Movement movement = new Movement("test", 5, 200, 300, 315, 1);
        mTestService.addNewMovement(movement);
        ArgumentCaptor<Movement> argumentCaptor = ArgumentCaptor.forClass(Movement.class);
        verify(mMovementRepository).save(argumentCaptor.capture());
        Movement capturedMovement = argumentCaptor.getValue();
        assertThat(capturedMovement).isEqualTo(movement);
    }

    @Test
    void addMovementInvalid() {
        Movement movement = new Movement("test", 5, 200, 300, 315, 1);
        when(mMovementRepository.findMovementByName(any()))
                .thenReturn(java.util.Optional.of(movement));
        assertThatThrownBy(()-> mTestService.addNewMovement(movement)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deleteMovement() {
        // Since we are mocking the repo there are no ids in the db
        assertThatThrownBy(()-> mTestService.deleteMovement(1)).isInstanceOf(IllegalStateException.class);
    }

//    @Test
//    void updateMovementReps() {
//        Movement mockMovement = mock(Movement.class);
//        when(mMovementRepository.findById(any()))
//                .thenReturn(java.util.Optional.of(mockMovement));
//        mTestService.updateMovementReps(1, "test",30,200);
//        verify(mockMovement).setNumReps(30);
//        verify(mockMovement).setRepWeight(200);
//    }
//
//    @Test
//    void updateMovementOneRepMax() {
//        Movement mockMovement = mock(Movement.class);
//        when(mMovementRepository.findById(any()))
//                .thenReturn(java.util.Optional.of(mockMovement));
//        mTestService.updateMovementOneRepMax(1, "test", 400);
//        verify(mockMovement).setOneRepMax(400);
//    }
}