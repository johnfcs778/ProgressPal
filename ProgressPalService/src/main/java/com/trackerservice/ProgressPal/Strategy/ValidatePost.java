package com.trackerservice.ProgressPal.Strategy;

import com.trackerservice.ProgressPal.Requests.PostWorkoutReq;
import com.trackerservice.ProgressPal.Requests.Request;
import com.trackerservice.ProgressPal.Workout.Workout;

import java.time.LocalDate;

/**
 * Concrete implementor of strategy which performs validation on a PostWorkout Request
 */
public class ValidatePost implements Strategy {

    @Override
    public boolean validate(Request request) {
        Workout check = ((PostWorkoutReq)request).getWorkoutToValidate();

        // Validate date
        if(!check.getDate().isBefore(LocalDate.now().plusDays(1))) {
            return false;
        }

        // Validate type
        if(check.getWorkoutType() == null ||
                check.getWorkoutType().isEmpty()) {
            return false;
        }

        // Validate notes
        if(check.getNotes() == null ||
            check.getNotes().isEmpty()) {
            return false;
        }

        return true;
    }
}
