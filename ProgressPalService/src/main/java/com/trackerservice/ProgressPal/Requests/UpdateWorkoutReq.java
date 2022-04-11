package com.trackerservice.ProgressPal.Requests;

import com.trackerservice.ProgressPal.Workout.Workout;

/**
 * Workout object encapsulating an UpdateWorkoutRequest
 */
public class UpdateWorkoutReq implements Request {

    private Workout workoutToCheck;
    private String workoutTypeUpdate;
    private String notesUpdate;

    @Override
    public RequestType getRequestType() {
        return RequestType.UpdateWorkout;
    }

    public UpdateWorkoutReq(Workout workoutToCheck, String workoutType, String notes) {
        this.workoutToCheck = workoutToCheck;
        this.workoutTypeUpdate = workoutType;
        this.notesUpdate = notes;
    }

    public Workout getWorkoutToCheck() {
        return workoutToCheck;
    }

    public String getWorkoutTypeUpdate() {
        return workoutTypeUpdate;
    }

    public String getNotesUpdate() {
        return notesUpdate;
    }
}
