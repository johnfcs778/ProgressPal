package com.trackerservice.ProgressPal.Movement;

import javax.persistence.*;

/**
 * Entity definition for mapping Movement objects to the database table
 */
@Entity(name="Movement")
@Table
public class Movement {
    @Id
    @SequenceGenerator(
            name = "movement_sequence",
            sequenceName = "movement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movement_sequence"
    )
    private int Id;
    @Column(
            name="movement_name",
            nullable = false
    )
    private String name;
    private int numReps;
    private double repWeight;
    private double oneRepMax;
    private double oneRepMaxGoal;
    private int userId;

    public Movement() {

    }

    public Movement(String name, int numReps, double repWeight, double oneRepMax, double oneRepMaxGoal, int userId) {
        this.name = name;
        this.numReps = numReps;
        this.repWeight = repWeight;
        this.oneRepMax = oneRepMax;
        this.oneRepMaxGoal = oneRepMaxGoal;
        this.userId = userId;
    }

    public int getId() {
        return Id;
    }

    public double getOneRepMaxGoal() {
        return oneRepMaxGoal;
    }

    public void setOneRepMaxGoal(double oneRepMaxGoal) {
        this.oneRepMaxGoal = oneRepMaxGoal;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String movementName) {
        this.name = movementName;
    }

    public int getNumReps() {
        return numReps;
    }

    public void setNumReps(int numReps) {
        this.numReps = numReps;
    }

    public double getRepWeight() {
        return repWeight;
    }

    public void setRepWeight(double repWeight) {
        this.repWeight = repWeight;
    }

    public double getOneRepMax() {
        return oneRepMax;
    }

    public void setOneRepMax(double oneRepMax) {
        this.oneRepMax = oneRepMax;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
