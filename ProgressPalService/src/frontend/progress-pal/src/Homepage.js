import { Jumbotron, Container, Image, Row, Col, Alert } from 'react-bootstrap';
import Axios from 'axios';
import dumbell from './dumbell.png';
import WorkoutCard from './Workouts/WorkoutCard';
import React, {useState} from 'react';

function Homepage(props) {
  const [workout, setWorkout] = useState();

  const getWorkouts = (num) => {
    console.log(props.token);
      Axios.get("http://localhost:8080/api/v1/workouts/recent/user/"+props.userId+"/"+num, {
        headers: {
          'Authorization': `Bearer ${props.token}` 
        }
      }).then((response)=> {
        let list = [];
        response.data.forEach((workout) => {
          setWorkout(<WorkoutCard workoutData={workout}></WorkoutCard>);
        })
      })
  }

  React.useEffect(()=> {
    getWorkouts(1);
  }, []);

    return (<div> 
      <Container fluid>
        <Row>
          <Col>
          <Jumbotron >
          <Container>
            <Row className="d-flex align-items-center justify-content-center">
              <h1 style={{marginRight: 20}}>Welcome to Progress Pal!</h1> 
              <Image src={dumbell} rounded height="40" width="40"/>
            </Row>
            <p className="d-flex align-items-center justify-content-center">
              Here you can view your workout and movement data.
            </p>
          </Container>
        </Jumbotron>
          </Col>
        </Row>
        <Row>
          <Col className="d-flex align-items-center justify-content-center" fluid>
            <Container >
              <Row className="d-flex align-items-center justify-content-center">  <Alert variant="success"><Alert.Heading>Your Most Recent Workout:</Alert.Heading></Alert></Row>
              <Row className="d-flex align-items-center justify-content-center">{workout}</Row>
            </Container>
          </Col>
        </Row>
      
        </Container>
        </div>);
}

export default Homepage;