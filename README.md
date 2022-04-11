# Progress Pal Service API
## Overview
The idea of ProgressPal is to provide a more focused workout tracking application for strength training. Users will be able to input data for workouts as well as specific movements and their movement milestones. Movement goals will be a future iteration of the service. The Progress Pal Service API is the backend api for this application. With the progresspal service api, clients can GET,POST,PUT, and DELETE to the workout service to insert, update, get, and remove both workout and movement data. This service is built with Java Spring Boot, using Spring Data JPA for Entity database mapping using Hibernate. The database used is a MySql database. 

## Authentication
Currently there is no authentication built into progresspal service. This will be added in the future using OAuth 2.0.

## Endpoints

### /api/v1/workouts
- GET - returns a JSON list of workouts in the format of workout objects
- GET (path=”/recent/{number}”) 
Returns a JSON list of the {number} most recent workouts in the database
- GET (path=”/bydate”) 
Returns a workout if there is one present on the given date
Requires parameters as follows:
{year} integer value of the year
{date} integer value of the date
{month} integer value of the month

- POST 
Req Body should contain a json workout object to be inserted in the database
- DELETE
Will take an integer @identifier in the path(“api/v1/workouts/1”) to delete the workout for @identifier
- PUT
Will take an integer @identifier in the path(“api/v1/workouts/1”) to update the workout for @identifier
Will take parameters: {workoutType} and {notes} 

### /api/v1/movements
- GET - returns a JSON list of movements in the format of movement objects
- POST 
Req Body should contain a json movement object to be inserted in the db
- DELETE
Will take an integer @identifier in the path(“api/v1/movements/1”) to delete the movement for @identifier
- PUT
Will take an integer @identifier in the path(“api/v1/movements/1”) to update the movement for @identifier
Will take parameters as one of the following options is required:
- Option1
{numReps} and {repWeight} to indicate updating an AMRAP 
{oneRepMax} to indicate updating a 1RM

### Example Object Structure
- Example Workout Object:
{
"id":1,
"workoutType":"Push",
"Date":"2021-04-15",
"Length":25.4,
"Notes":"fun",
"milestoneReached":true
}

- Example Movement Object:
{
"name":"Bench Press",
"numReps":4,
"repWeight":54.0,
"oneRepMax":230.0,
"Id":1
}


# Progress Pal Frontend

* Progress Pal frontend is a create-react-app application using react-boostrap for styling and Axios for making api requests
* The frontend currently supports basic functions such as viewing the most recent 1,5 or 10 workouts, Getting a workout from a specific date, as well as showing the movements in the database. 

# Testing 
- Testing is done using JUnit, Mockito, and SpringBoot test

# Patterns
- Various patterns used in the project include the Composite pattern used to create a class hierarchy of RequestObjects, and the Strategy pattern used to perform validation on the various RequestObjects at runtime with varying implementations of the Validate strategy
