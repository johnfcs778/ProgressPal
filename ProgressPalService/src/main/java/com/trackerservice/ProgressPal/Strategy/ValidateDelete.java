package com.trackerservice.ProgressPal.Strategy;

import com.trackerservice.ProgressPal.Requests.DeleteWorkoutReq;
import com.trackerservice.ProgressPal.Requests.Request;
import com.trackerservice.ProgressPal.Workout.Workout;
import com.trackerservice.ProgressPal.Workout.WorkoutRepository;

import java.util.Optional;

/**
 * Concrete implementor of strategy which performs validation on a DeleteWorkout Request
 */
public class ValidateDelete implements Strategy {

    private final WorkoutRepository mWorkoutRepository;

    public ValidateDelete(WorkoutRepository workoutRepository) {
        this.mWorkoutRepository = workoutRepository;
    }

    @Override
    public boolean validate(Request request) {
        // If the given workout does not exist, the request is invalid
        int idToCheck = ((DeleteWorkoutReq)request).getIdToCheck();
        Optional<Workout> workout = mWorkoutRepository.findWorkoutById(idToCheck);
        if(!workout.isPresent()){
            return false;
        }
        return true;
    }
}
