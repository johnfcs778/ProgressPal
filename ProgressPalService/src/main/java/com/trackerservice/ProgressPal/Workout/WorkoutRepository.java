package com.trackerservice.ProgressPal.Workout;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Repository interface extending the JpaRepository to allow use of JpaRepository default
 * methods as well as extending methods to find by properties of our specific Entities. This
 * does not actually need to be implemented since the function names are inherently picked up by Jpa
 * to perform the query
 */
@Repository
public interface WorkoutRepository
        extends JpaRepository<Workout,Integer> {

    @Query("SELECT s FROM Workout s WHERE s.length = ?1")
    Optional<Workout> findWorkoutByLength(double length);

    @Query("SELECT s FROM Workout s WHERE s.id = ?1")
    Optional<Workout> findWorkoutById(int id);

    Optional<Workout> findWorkoutByDate(LocalDate date);
}
