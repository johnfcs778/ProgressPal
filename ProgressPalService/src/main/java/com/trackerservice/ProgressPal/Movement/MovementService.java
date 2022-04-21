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
     * Gets all movements in the database for a user
     */
    public List<Movement> getMovementsForUser(int userId) {
        return mMovementRepository.findMovementsByUserId(userId);
    }

    /**
     * Gets a movement in the database for a user
     * @return
     */
    public Movement getMovementForUser(String name, int userId) {
        return mMovementRepository.findMovementByNameAndUserId(name, userId)
                .orElseThrow(() -> new IllegalStateException("Movement doesn't exist for user"));
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
     * Adds a new movement in the database given the Movement for a user
     * object from the request
     * @param movement
     */
    public void addNewMovementForUser(Movement movement, int userId) {

        Optional<Movement> movementByName = mMovementRepository.findMovementByNameAndUserId(movement.getName(), userId);
        if(movementByName.isPresent()) {
            throw new IllegalStateException("This movement already exists");
        }
        movement.setUserId(userId);
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
     * @param name
     * @param userId
     * @param numReps
     * @param repWeight
     */
    @Transactional
    // Transactional annotation allows modification of object to directly update in db
    public void updateMovementReps(int userId, String name, int numReps, double repWeight) {
        Movement movement = mMovementRepository.findMovementByNameAndUserId(name, userId)
                .orElseThrow(() -> new IllegalStateException("Movement doesn't exist"));

        movement.setNumReps(numReps);

        movement.setRepWeight(repWeight);

    }

    /**
     * Updates a movements in the database at the given identifier and
     * updates the oneRepMax
     * @param name
     * @param userId
     * @param oneRepMax
     */
    @Transactional
    // Transactional annotation allows modification of object to directly update in db
    public void updateMovementOneRepMax(int userId, String name, double oneRepMax) {
        Movement movement = mMovementRepository.findMovementByNameAndUserId(name, userId)
                .orElseThrow(() -> new IllegalStateException("Movement doesn't exist"));

        movement.setOneRepMax(oneRepMax);

    }

    /**
     * Updates a movements in the database at the given identifier and
     * updates the oneRepMax
     * @param name
     * @param userId
     * @param oneRepMaxGoal
     */
    @Transactional
    // Transactional annotation allows modification of object to directly update in db
    public void updateMovementOneRepMaxGoal(int userId, String name, double oneRepMaxGoal) {
        Movement movement = mMovementRepository.findMovementByNameAndUserId(name, userId)
                .orElseThrow(() -> new IllegalStateException("Movement doesn't exist"));

        movement.setOneRepMaxGoal(oneRepMaxGoal);

    }

}
