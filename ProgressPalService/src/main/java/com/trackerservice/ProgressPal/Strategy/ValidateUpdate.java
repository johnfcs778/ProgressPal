package com.trackerservice.ProgressPal.Strategy;

import com.trackerservice.ProgressPal.Requests.Request;
import com.trackerservice.ProgressPal.Requests.UpdateWorkoutReq;
import com.trackerservice.ProgressPal.Workout.Workout;

/**
 * Concrete implementor of strategy which performs validation on an UpdateWorkout Request
 */
public class ValidateUpdate implements Strategy {

    @Override
    public boolean validate(Request request) {
        Workout workout = ((UpdateWorkoutReq)request).getWorkoutToCheck();
        String workoutType = ((UpdateWorkoutReq)request).getNotesUpdate();
        String notes = ((UpdateWorkoutReq)request).getNotesUpdate();

        // Validate the workout type
        if(workoutType == null ||
                workoutType.isEmpty() ||
                workout.getWorkoutType().equals(workoutType)) {
            return false;
        }

        // Validate the notes
        if(notes == null ||
                notes.isEmpty() ||
                workout.getNotes().equals(notes)) {
            return false;
        }
        return true;
    }
}
