import React from 'react';
import { ProgressBar } from 'react-bootstrap';
import Card from 'react-bootstrap/Card';

const MovementCard = ({movementData}) => {
    if(movementData === undefined || Object.keys(movementData).length === 0) {
        return null;
    } else {
      let progressVal = 0;
      if(movementData.oneRepMaxGoal !== 0) {
        progressVal = Math.floor(movementData.oneRepMax/movementData.oneRepMaxGoal * 100);
      }
        return (
          <div style={{marginTop: 10, marginBottom: 10}}>
            <Card style={{ width: '18rem' }}>
            <Card.Body>
              <Card.Title>Name: {movementData.name}</Card.Title>
              <Card.Text>
                AMRAP: {movementData.numReps} reps, {movementData.repWeight} lbs
              </Card.Text>
              <Card.Text>
                1RM: {movementData.oneRepMax} lbs
              </Card.Text>
              <Card.Text>
                1RM Goal: {movementData.oneRepMaxGoal} lbs
              </Card.Text>
              <Card.Footer>
                Progress to goal:
                <ProgressBar animated now={progressVal} label={`${progressVal}%`}></ProgressBar>
              </Card.Footer>
            </Card.Body>
          </Card>
          </div>
        );
    }
}

export default MovementCard;