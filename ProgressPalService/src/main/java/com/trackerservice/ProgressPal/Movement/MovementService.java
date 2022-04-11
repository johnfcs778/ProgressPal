package com.trackerservice.ProgressPal.Movement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service to interact with the repository and perform the database transactions given the request
 */
@Service
public class MovementService {
    private final MovementRepository mMovementRepository;

    @Autowired
    public MovementService(MovementRepository movementRepository) {
        this.mMovementRepository = movementRepository;
    }

    /**
     * Gets all movements in the database
     */
    public List<Movement> getMovements() {
        return mMovementRepository.findAll();
    }

    /**
     * Adds a new movement in the database given the Movement
     * object from the request
     * @param movement
     */
    public void addNewMovement(Movement movement) {

        Optional<Movement> movementByName = mMovementRepository.findMovementByName(movement.getName());
        if(movementByName.isPresent()) {
            throw new IllegalStateException("This movement already exists");
        }

        mMovementRepository.save(movement);

    }

    /**
     * Deletes a movement from the database given an id from the request
     * @param id
     */
    public void deleteMovement(Integer id) {
        Optional<Movement> movement = mMovementRepository.findById(id);
        if(!movement.isPresent()){
            throw new IllegalStateException("Workout Id does not exist");
        }
        mMovementRepository.deleteById(id);
    }

    /**
     * Updates a movement in the database at the given identifier and
     * updates the numReps and repWeight values
     * @param Id
     * @param numReps
     * @param repWeight
     */
    @Transactional
    // Transactional annotation allows modification of object to directly update in db
    public void updateMovementReps(Integer Id, int numReps, double repWeight) {
        Movement movement = mMovementRepository.findById(Id)
                .orElseThrow(() -> new IllegalStateException("Movement Id doesn't exist"));

        movement.setNumReps(numReps);

        movement.setRepWeight(repWeight);

    }

    /**
     * Updates a movements in the database at the given identifier and
     * updates the oneRepMax
     * @param Id
     * @param oneRepMax
     */
    @Transactional
    // Transactional annotation allows modification of object to directly update in db
    public void updateMovementOneRepMax(Integer Id, double oneRepMax) {
        Movement movement = mMovementRepository.findById(Id)
                .orElseThrow(() -> new IllegalStateException("Movement Id doesn't exist"));

        movement.setOneRepMax(oneRepMax);

    }

    /**
     * Updates a movements in the database at the given identifier and
     * updates the oneRepMax
     * @param Id
     * @param oneRepMaxGoal
     */
    @Transactional
    // Transactional annotation allows modification of object to directly update in db
    public void updateMovementOneRepMaxGoal(Integer Id, double oneRepMaxGoal) {
        Movement movement = mMovementRepository.findById(Id)
                .orElseThrow(() -> new IllegalStateException("Movement Id doesn't exist"));

        movement.setOneRepMaxGoal(oneRepMaxGoal);

    }

}
