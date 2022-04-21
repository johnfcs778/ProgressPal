package com.trackerservice.ProgressPal.Workout;

import com.trackerservice.ProgressPal.Requests.DeleteWorkoutReq;
import com.trackerservice.ProgressPal.Requests.PostWorkoutReq;
import com.trackerservice.ProgressPal.Requests.UpdateWorkoutReq;
import com.trackerservice.ProgressPal.Strategy.Context;
import com.trackerservice.ProgressPal.Strategy.ValidateDelete;
import com.trackerservice.ProgressPal.Strategy.ValidatePost;
import com.trackerservice.ProgressPal.Strategy.ValidateUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service to interact with the repository and perform the database transactions given the request
 */
@Service
public class WorkoutService {

    private final WorkoutRepository mWorkoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.mWorkoutRepository = workoutRepository;
    }

    /**
     * Gets all workouts in the database
     */
    public List<Workout> getWorkouts() {
        return mWorkoutRepository.findAll();
    }

    /**
     * Adds a new workout in the database given the Workout
     * object from the request
     * @param workout
     */
    public void addNewWorkout(Workout workout) {
        Context context = new Context(new ValidatePost());
        if(context.execute(new PostWorkoutReq(workout))) {
            mWorkoutRepository.save(workout);
        } else {
            throw new IllegalStateException("Workout object invalid");
        }

    }

    /**
     * Adds a new workout in the database given the Workout
     * object from the request
     * @param workout
     */
    public void addNewWorkoutForUser(Workout workout, int userId) {
        Context context = new Context(new ValidatePost());
        if(context.execute(new PostWorkoutReq(workout))) {
            workout.setUserId(userId);
            mWorkoutRepository.save(workout);
        } else {
            throw new IllegalStateException("Workout object invalid");
        }

    }

    /**
     * Gets all movements in the database for a user
     */
    public List<Workout> getWorkoutsForUser(int userId) {
        return mWorkoutRepository.findWorkoutsByUserId(userId);
    }

    /**
     * Gets a movement in the database for a user
     * @return
     */
    public Workout getWorkoutForUser(int id, int userId) {
        return mWorkoutRepository.findWorkoutByIdAndUserId(id, userId)
                .orElseThrow(() -> new IllegalStateException("Workout doesn't exist for user"));
    }

    /**
     * Deletes a workout from the database given an id from the request
     * @param id
     */
    public void deleteWorkout(Integer id) {
        Context context = new Context(new ValidateDelete(mWorkoutRepository));
        if(context.execute(new DeleteWorkoutReq(id))) {
            mWorkoutRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Workout Id does not exist");
        }
    }

    /**
     * Updates a workout in the database at the given identifier and
     * updates the workoutType and notes values
     * @param workoutId
     * @param workoutType
     * @param notes
     */
    @Transactional
    // Transactional annotation allows modification of object to directly update in db
    public void updateWorkout(Integer workoutId, String workoutType, String notes) {
        Workout workout = mWorkoutRepository.findById(workoutId)
                .orElseThrow(() -> new IllegalStateException("Workout Id doesn't exist"));
        Context context = new Context(new ValidateUpdate());
        if(context.execute(new UpdateWorkoutReq(workout, workoutType, notes))) {
            workout.setWorkoutType(workoutType);
            workout.setNotes(notes);
        }else {
            throw new IllegalStateException("workoutType or notes param is invalid");
        }

    }

    /**
     * Gets the {number} most recent workouts in the database
     * @param number
     * @return
     */
    public List<Workout> getRecentWorkouts(Integer number) {

        // Get whole list of workouts
        List<Workout> wList =  mWorkoutRepository.findAll();

        // Sort the list by date descending
        wList.sort(Comparator.comparing(Workout::getDate).reversed());

        // return the first x in the list
        return wList.stream().limit(number).collect(Collectors.toList());
    }

    /**
     * Gets a specific workout for a given date, returns null if none exists
     * @param year
     * @param day
     * @param month
     * @return
     */
    public Workout getWorkoutByDate(int year, int day, int month) {
        Optional<Workout> workout = mWorkoutRepository.findWorkoutByDate(LocalDate.of(year, month, day));
        if(workout.isPresent()) {
            return workout.get();
        } else {
            return null;
        }
    }

    /**
     * Gets the {number} most recent workouts in the database for a User
     * @param number
     * @return
     */
    public List<Workout> getRecentWorkoutsForUser(Integer number, Integer userId) {

        // Get whole list of workouts
        List<Workout> wList =  mWorkoutRepository.findWorkoutsByUserId(userId);

        // Sort the list by date descending
        wList.sort(Comparator.comparing(Workout::getDate).reversed());

        //TODO: optimize this, just grab from database by userID
//        wList = wList.stream()
//                .filter(workout -> workout.getUserId() == userId)
//                .collect(Collectors.toList());

        // return the first x in the list
        return wList.stream().limit(number).collect(Collectors.toList());
    }

    /**
     * Gets a specific workout for a given date for a user, returns null if none exists
     * @param year
     * @param day
     * @param month
     * @return
     */
    public List<Workout> getWorkoutsByDateForUser(int year, int day, int month, Integer userId) {
        List<Workout> workouts = mWorkoutRepository.findWorkoutsByDateAndUserId(LocalDate.of(year, month, day), userId);
        if(workouts.size() > 0) {
            return workouts;
        } else {
            return null;
        }
    }
}
