package com.trackerservice.ProgressPal.Controllers;

import com.trackerservice.ProgressPal.Workout.Workout;
import com.trackerservice.ProgressPal.Workout.WorkoutService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling GET,POST,PUT, and DELETE for Workouts
 */
@RestController
@RequestMapping(path = "api/v1/workouts")
public class WorkoutController {

    private final WorkoutService mWorkoutService;

    @Autowired
    public WorkoutController (WorkoutService workoutService) {
        this.mWorkoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getWorkouts() {
        return mWorkoutService.getWorkouts();
    }

    @GetMapping("/recent/{number}")
    public List<Workout> getRecentWorkouts(@PathVariable("number") Integer number) {
        return mWorkoutService.getRecentWorkouts(number);
    }

    // Get Recent workouts for User
    @GetMapping("/recent/user/{userId}/{number}")
    public List<Workout> getRecentWorkoutsForUser(@PathVariable("userId") Integer userId, @PathVariable("number") Integer number) {
        return mWorkoutService.getRecentWorkoutsForUser(number, userId);
    }

    // Get workouts by date for User
    @GetMapping("/user/bydate/{userId}")
    public List<Workout> getWorkoutByDateForUser(@PathVariable("userId") Integer userId,
                                           @RequestParam() int year,
                                    @RequestParam() int day,
                                    @RequestParam() int month ) {
        return mWorkoutService.getWorkoutsByDateForUser(year,day,month, userId);
    }

    @GetMapping("/bydate")
    public Workout getWorkoutByDate(@RequestParam() int year,
                                          @RequestParam() int day,
                                          @RequestParam() int month ) {
        return mWorkoutService.getWorkoutByDate(year,day,month);
    }

    @GetMapping(path = "/user/{userId}")
    public List<Workout> getWorkoutsForUser(@PathVariable("userId") Integer Id) {
        return mWorkoutService.getWorkoutsForUser(Id);
    }

    @GetMapping(path = "/user/specific/{userId}")
    public Workout getWorkoutForUser(@PathVariable("userId") Integer userId, @RequestParam(required = true) int id) {
        return mWorkoutService.getWorkoutForUser(id, userId);
    }

    @PostMapping
    public void addWorkout(@RequestBody Workout workout) {
        mWorkoutService.addNewWorkout(workout);
    }

    @DeleteMapping(path = "{workoutId}")
    public void deleteWorkout(@PathVariable("workoutId") Integer id) {
        mWorkoutService.deleteWorkout(id);
    }

    @PutMapping(path = "{workoutId}")
    public void updateWorkout(@PathVariable("workoutId") Integer workoutId,
                              @RequestParam(required = false) String workoutType,
                              @RequestParam(required = false) String notes) {
        mWorkoutService.updateWorkout(workoutId, workoutType, notes);
    }
}
