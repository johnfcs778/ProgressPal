import React, { useState } from "react";
import movement from "../movement.png";
import MovementCard from "./MovementCard";
import Axios from "axios";
import Popup from "reactjs-popup";
import "reactjs-popup/dist/index.css";
import Form from "react-bootstrap/Form";
import { Button, Container, Image, Col, Row } from "react-bootstrap";

const MovementPage = (props) => {
  const [movementList, setMovementList] = useState([]);

  const getMovements = () => {
    console.log(props.userId);
    Axios.get("http://localhost:8080/api/v1/movements/user/" + props.userId, {
      headers: {
        Authorization: `Bearer ${props.token}`,
      },
    }).then((response) => {
      let list = [];
      response.data.forEach((movement) => {
        list.push(
          <MovementCard
            movementData={movement}
            getMovements={getMovements}
            token={props.token}
            userId={props.userId}
          ></MovementCard>
        );
      });
      setMovementList(list);
    });
  };

  const addMovement = (name, numReps, repWeight, oneRepMax, oneRepMaxGoal) => {
    Axios.post(
      "http://localhost:8080/api/v1/movements/user/" + props.userId,
      {
        name: name,
        numReps: numReps,
        repWeight: repWeight,
        oneRepMax: oneRepMax,
        oneRepMaxGoal: oneRepMaxGoal,
      },
      {
        headers: {
          Authorization: `Bearer ${props.token}`,
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

  function handleSubmit(event) {
    addMovement(
      event.target[0].value,
      event.target[1].value,
      event.target[2].value,
      event.target[3].value,
      event.target[4].value
    );
    event.preventDefault();
    document.getElementById("closebutton").click();
  }

  return (
    <div>
      <Container fluid>
        <Row fluid>
          <Col fluid>
            <nav className="d-flex align-items-center justify-content-center navbar navbar-light bg-light">
              <div className="navbar-brand">
                <Button
                  id="showMovementsButton"
                  style={{
                    marginLeft: 10,
                    marginRight: 10,
                  }}
                  variant="primary"
                  onClick={() => {
                    getMovements();
                  }}
                >
                  {" "}
                  Show Movements
                </Button>
                <Image
                  src={movement}
                  rounded
                  height="40"
                  width="40"
                  style={{
                    marginRight: 10,
                  }}
                />
                Movements
                <Popup
                  trigger={
                    <Button
                      style={{
                        marginLeft: 10,
                      }}
                    >
                      Add New Movement
                    </Button>
                  }
                  position="right center"
                  modal
                  nested
                >
                  {(close) => (
                    <div>
                      <button
                        id="closebutton"
                        className="close"
                        onClick={close}
                      >
                        &times;
                      </button>
                      <Form onSubmit={handleSubmit}>
                        <Form.Group size="lg" controlId="email">
                          <h2 style={{ marginBottom: 20 }}>
                            {" "}
                            Add a new Movement{" "}
                          </h2>
                          <Form.Label>
                            <b>Movement Name</b>
                          </Form.Label>
                          <Form.Control
                            style={{ marginBottom: 20 }}
                            autoFocus
                            type="text"
                            placeholder="Bench Press"
                          />
                          <Form.Label>
                            <b>AMRAP Reps</b>
                          </Form.Label>
                          <Form.Control
                            style={{ marginBottom: 20 }}
                            autoFocus
                            type="number"
                            placeholder={0}
                          />
                          <Form.Label>
                            <b>AMRAP Weight</b>
                          </Form.Label>
                          <Form.Control
                            style={{ marginBottom: 20 }}
                            autoFocus
                            type="number"
                            placeholder={0}
                          />
                          <Form.Label>
                            <b>One Rep Max</b>
                          </Form.Label>
                          <Form.Control
                            style={{ marginBottom: 20 }}
                            autoFocus
                            type="number"
                            placeholder={0}
                          />
                          <Form.Label>
                            <b>One Rep Max Goal</b>
                          </Form.Label>
                          <Form.Control
                            style={{ marginBottom: 20 }}
                            autoFocus
                            type="number"
                            placeholder={0}
                          />
                        </Form.Group>
                        <Button block size="lg" type="submit">
                          Add
                        </Button>
                      </Form>
                    </div>
                  )}
                </Popup>
              </div>
            </nav>
          </Col>
        </Row>
        <Row>
          <Col>
            <Container>
              <Row>
                <Col>{movementList[0]}</Col>
                <Col>{movementList[1]}</Col>
                <Col>{movementList[2]}</Col>
              </Row>
              <Row>
                <Col>{movementList[3]}</Col>
                <Col>{movementList[4]}</Col>
                <Col>{movementList[5]}</Col>
              </Row>
            </Container>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default MovementPage;
