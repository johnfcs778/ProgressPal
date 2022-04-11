package com.trackerservice.ProgressPal.Workout;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity definition for mapping Workout objects to the database table
 */
@Entity(name="Workout")
@Table
public class Workout {
    @Id
    @SequenceGenerator(
            name = "workout_sequence",
            sequenceName = "workout_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "workout_sequence"
    )
    @Column(
            name="id",
            nullable = false,
            updatable = false
    )
    private int id;

    @Column(
            nullable = false
    )
    private String workoutType;

    @Column(
            nullable = false
    )
    private LocalDate date;
    private double length;
    private String notes;

    @Column(
            nullable = false
    )
    private boolean milestoneReached;

    public Workout() {
    }

    public Workout(int id) {
        this.id = id;
    }

    public Workout(String workoutType, LocalDate date, double length, String notes, boolean milestoneReached) {
        this.workoutType = workoutType;
        this.date = date;
        this.length = length;
        this.notes = notes;
        this.milestoneReached = milestoneReached;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isMilestoneReached() {
        return milestoneReached;
    }

    public void setMilestoneReached(boolean milestoneReached) {
        this.milestoneReached = milestoneReached;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", workoutType='" + workoutType + '\'' +
                ", date=" + date +
                ", length=" + length +
                ", notes='" + notes + '\'' +
                ", milestoneReached=" + milestoneReached +
                '}';
    }
}
