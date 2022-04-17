import React, {useState} from 'react';
import movement from '../movement.png';
import MovementCard from './MovementCard';
import Axios from 'axios';
import { Button, Container, Image, Col, Row} from 'react-bootstrap';


const MovementPage = () => { 
    const [movementList, setMovementList] = useState([]);

    const getMovements = () => {
        Axios.get("http://localhost:8080/api/v1/movements").then((response)=> {
          let list = [];
          response.data.forEach((movement) => {
            list.push(<MovementCard movementData={movement}></MovementCard>);
          })
          setMovementList(list);
        })
      }


  return <div>
        <nav className="navbar navbar-light bg-light">
          <div className="navbar-brand">
          <Image src={movement} rounded height="40" width="40" style={{
          marginRight: 10
        }} />
            Movements
          </div>
        </nav>
        <Button style={{
      marginLeft: 10
    }} variant="primary" onClick={() => {
      getMovements();
    }}> Show Movements</Button>
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
      </div>;
}
  
export default MovementPage;