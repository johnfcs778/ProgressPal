package com.trackerservice.ProgressPal.Requests;

import com.trackerservice.ProgressPal.Workout.Workout;

/**
 * Workout object encapsulating a PostWorkoutRequest
 */
public class PostWorkoutReq implements Request{

    private Workout workoutToValidate;

    @Override
    public RequestType getRequestType() {
        return RequestType.PostWorkout;
    }

    public PostWorkoutReq(Workout workoutToValidate) {
        this.workoutToValidate = workoutToValidate;
    }

    public Workout getWorkoutToValidate() {
        return workoutToValidate;
    }
}
