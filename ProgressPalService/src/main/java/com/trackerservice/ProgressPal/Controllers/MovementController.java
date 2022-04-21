package com.trackerservice.ProgressPal.Controllers;

import com.trackerservice.ProgressPal.Movement.Movement;
import com.trackerservice.ProgressPal.Movement.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling GET,POST,PUT, and DELETE for Movements
 */
@RestController
@RequestMapping(path = "api/v1/movements")
public class MovementController {

    private final MovementService mMovementService;

    @Autowired
    public MovementController (MovementService movementService) {
        this.mMovementService = movementService;
    }

    @GetMapping
    public List<Movement> getMovements() {
        return mMovementService.getMovements();
    }

    @GetMapping(path = "/user/{userId}")
    public List<Movement> getMovementsForUser(@PathVariable("userId") Integer Id) {
        return mMovementService.getMovementsForUser(Id);
    }

    @GetMapping(path = "/user/specific/{userId}")
    public Movement getMovementForUser(@PathVariable("userId") Integer Id, @RequestParam(required = true) String name) {
        return mMovementService.getMovementForUser(name, Id);
    }

    @PostMapping(path = "/user/{userId}")
    public void addMovement(@PathVariable("userId") Integer Id, @RequestBody Movement movement) {
        mMovementService.addNewMovementForUser(movement, Id);
    }

    @DeleteMapping(path = "{movementId}")
    public void deleteMovement(@PathVariable("movementId") Integer id) {
        mMovementService.deleteMovement(id);
    }

    @PutMapping(path = "{movementId}")
    public void updateMovement(@PathVariable("movementId") Integer Id,
                              @RequestParam(required = false) Integer numReps,
                              @RequestParam(required = false) Double repWeight,
                              @RequestParam(required = false) Double oneRepMax,
                              @RequestParam(required = false) Double oneRepMaxGoal) {
        if(oneRepMax != null) {
            mMovementService.updateMovementOneRepMax(Id, oneRepMax);
        } else if(repWeight != null && numReps != null) {
            mMovementService.updateMovementReps(Id, numReps, repWeight);
        } else if(oneRepMaxGoal != null) {
            mMovementService.updateMovementOneRepMaxGoal(Id, oneRepMaxGoal);
        }
        else {
            throw new IllegalStateException("Either one rep max OR repWeight & numReps OR oneRepMaxGoal must be present");
        }
    }
}
