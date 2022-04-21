import React from "react";
import { ProgressBar, Button } from "react-bootstrap";
import Card from "react-bootstrap/Card";
import Axios from "axios";
import Popup from "reactjs-popup";
import "reactjs-popup/dist/index.css";
import Form from "react-bootstrap/Form";

const MovementCard = ({ movementData, getMovements, token, userId }) => {
  function handleSubmit(event) {
    updateMovement(event.target[0].value, event.target[1].value, event.target[2].value, event.target[3].value, event.target[4].value)
    event.preventDefault();
    document.getElementById("closebutton").click();
  }

  const updateMovement = (name, numReps, repWeight, oneRepMax, oneRepMaxGoal) => {
    Axios.put(
      "https://progress-pal.herokuapp.com/api/v1/movements/user/" + userId + "/" + name,
      null,
      {
        params: {
          numReps,
          repWeight,
          oneRepMax,
          oneRepMaxGoal
        },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    ).then((response) => {
      if (response["status"] === 200) {
        getMovements();
      } else {
        // show error
      }
      console.log(response);
    });
  };

  if (movementData === undefined || Object.keys(movementData).length === 0) {
    return null;
  } else {
    let progressVal = 0;
    if (movementData.oneRepMaxGoal !== 0) {
      progressVal = Math.floor(
        (movementData.oneRepMax / movementData.oneRepMaxGoal) * 100
      );
    }
    return (
      <div style={{ marginTop: 10, marginBottom: 10 }}>
        <Card border="secondary" style={{ width: "18rem" }}>
          <Card.Body>
            <Card.Title>Name: {movementData.name}</Card.Title>
            <Card.Text>
              AMRAP: {movementData.numReps} reps, {movementData.repWeight} lbs
            </Card.Text>
            <Card.Text>1RM: {movementData.oneRepMax} lbs</Card.Text>
            <Card.Text>1RM Goal: {movementData.oneRepMaxGoal} lbs</Card.Text>
            <Card.Footer>
              Progress to goal:
              <ProgressBar
                animated
                now={progressVal}
                label={`${progressVal}%`}
              ></ProgressBar>
            </Card.Footer>
            <Popup
              trigger={
                <Button
                  style={{ marginLeft: 80, marginTop: 20 }}
                  variant="primary"
                >
                  Update
                </Button>
              }
              position="right center"
              modal
              nested
            >
              {(close) => (
                <div>
                  <button id="closebutton" className="close" onClick={close}>
                    &times;
                  </button>
                  <Form onSubmit={handleSubmit}>
                    <Form.Group size="lg" controlId="email">
                      <h2 style={{ marginBottom: 20 }}> Update Movement </h2>
                      <Form.Label>
                        <b>Movement Name</b>
                      </Form.Label>
                      <Form.Control
                        style={{ marginBottom: 20 }}
                        autoFocus
                        type="text"
                        placeholder="Bench Press"
                        value={movementData.name}
                      />
                      <Form.Label>
                        <b>AMRAP Reps</b>
                      </Form.Label>
                      <Form.Control
                        style={{ marginBottom: 20 }}
                        autoFocus
                        type="number"
                        placeholder={movementData.numReps}
                      />
                      <Form.Label>
                        <b>AMRAP Weight</b>
                      </Form.Label>
                      <Form.Control
                        style={{ marginBottom: 20 }}
                        autoFocus
                        type="number"
                        placeholder={movementData.repWeight}
                      />
                      <Form.Label>
                        <b>One Rep Max</b>
                      </Form.Label>
                      <Form.Control
                        style={{ marginBottom: 20 }}
                        autoFocus
                        type="number"
                        placeholder={movementData.oneRepMax}
                      />
                      <Form.Label>
                        <b>One Rep Max Goal</b>
                      </Form.Label>
                      <Form.Control
                        style={{ marginBottom: 20 }}
                        autoFocus
                        type="number"
                        placeholder={movementData.oneRepMaxGoal}
                      />
                    </Form.Group>
                    <Button block size="lg" type="submit">
                      Update
                    </Button>
                  </Form>
                </div>
              )}
            </Popup>
          </Card.Body>
        </Card>
      </div>
    );
  }
};

export default MovementCard;
