package com.trackerservice.ProgressPal.Requests;


/**
 * Workout object encapsulating a DeleteWorkoutRequest
 */
public class DeleteWorkoutReq implements Request {

    private int idToCheck;

    @Override
    public RequestType getRequestType() {
        return RequestType.DeleteWorkout;
    }

    public DeleteWorkoutReq(int idToCheck) {
        this.idToCheck = idToCheck;
    }

    public int getIdToCheck() {
        return idToCheck;
    }
}
