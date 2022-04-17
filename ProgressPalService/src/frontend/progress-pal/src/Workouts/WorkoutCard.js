import React from 'react';
import Card from 'react-bootstrap/Card';

const WorkoutCard = ({workoutData}) => {
    if(workoutData === undefined || Object.keys(workoutData).length === 0) {
        return null;
    } else {
        return (
          <div style={{marginTop: 10, marginBottom: 10}}>
            <Card style={{ width: '18rem' }}>
            <Card.Body>
              <Card.Title>Date: {workoutData.date}</Card.Title>
              <Card.Subtitle className="mb-2 text-muted">Type: {workoutData.workoutType}</Card.Subtitle>
              <Card.Text>
                Length: {workoutData.length} minutes
              </Card.Text>
              <Card.Text>
                Notes: {workoutData.notes}
              </Card.Text>
              <Card.Text>
                Milestone Reached?: {String(workoutData.milestoneReached)}
              </Card.Text>
            </Card.Body>
          </Card>
          </div>
        );
    }
}

export default WorkoutCard;