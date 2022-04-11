package com.trackerservice.ProgressPal.Movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovementRepository
        extends JpaRepository<Movement,Integer> {

    //SELECT s FROM Movement s WHERE s.movementName = name
    Optional<Movement> findMovementByName(String name);

}
