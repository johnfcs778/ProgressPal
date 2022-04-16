import './App.css';
import Axios from 'axios';
import Button from 'react-bootstrap/Button';
import React, {useState, createContext} from 'react';
import WorkoutCard from './WorkoutCard';
import MovementCard from './MovementCard';
import 'bootstrap/dist/css/bootstrap.css';
import { Dropdown, DropdownButton, Nav, Container, Image, Col, Row, Pagination } from 'react-bootstrap';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import dumbell from './dumbell.png';
import movement from './movement.png';
import Homepage from './Homepage';
import LoginPage from './LoginPage';

function App() {

  const [workoutList, setWorkoutList] = useState([]);
  const [movementList, setMovementList] = useState([]);
  const [startDate, setStartDate] = useState(new Date());
  const [workoutByDate, setWorkoutByDate] = useState(<MovementCard></MovementCard>);
  const [currentPage, setCurrentPage] = useState("login");
  const [pagItems, setPagItems] = useState([]);
  const [woPageNum, setWoPageNum] = useState(1);
  const [currentWoIndex, setCurrentWoIndex] = useState(1);

  const getWorkouts = (num) => {
      Axios.get("http://localhost:8080/api/v1/workouts/recent/"+num).then((response)=> {
        let list = [];
        response.data.forEach((workout) => {
          list.push(<WorkoutCard workoutData={workout}></WorkoutCard>);
        })
        setWorkoutList(list);
        setCurrentWoIndex(1);
        setWoPageNum(1);
        renderPagItems(list.length);
      })
  }

  const renderPagItems = (num) => {
    let items = [];
    for(let i = 1; i <= Math.ceil(num / 6); i++) {
      items.push(
        <Pagination.Item key={i} active={i === woPageNum} onClick={()=>{setWoPageNum(i); setCurrentWoIndex(6 * i + -5); renderPagItems(num);}}>
          {i}
        </Pagination.Item>,
      );
    }
    setPagItems(items);
  }

  const getMovements = () => {
    Axios.get("http://localhost:8080/api/v1/movements").then((response)=> {
      let list = [];
      response.data.forEach((movement) => {
        list.push(<MovementCard movementData={movement}></MovementCard>);
      })
      setMovementList(list);
    })
  }

  const getWorkoutByDate = (date) => {
    setStartDate(date);
    Axios.get("http://localhost:8080/api/v1/workouts/bydate", {
      params: {
        year: date.getFullYear(),
        day: date.getDate(),
        month: date.getMonth() + 1
      }
    }).then((response)=> {
      setWorkoutByDate(<WorkoutCard workoutData={response.data}></WorkoutCard>)
    })
  }

  React.useEffect(()=> {
    renderPagItems(workoutList.length);
  },[woPageNum, workoutList]);

  return (
    <div>
      {currentPage !== 'login' && <Nav className="justify-content-center">
        <Nav.Item>
          <Nav.Link  as="button" onClick={()=>{setCurrentPage('home');}}>Home</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link as="button" onClick={()=>{setCurrentPage('workouts');}}>Workouts</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link as="button" onClick={()=>{setCurrentPage('movements');}}>Movements</Nav.Link>
        </Nav.Item>
      </Nav>}
      
      {/* Login Page */}
      {currentPage === 'login' && 
        <LoginPage></LoginPage>
      }

      {/* Home Page */}
      {currentPage === 'home' && 
        <Homepage></Homepage>
      }

      {/* Workouts Page */}
      {currentPage === 'workouts' && <div className="justify-content-center">
        <nav className="navbar navbar-light bg-light">
        <div className="navbar-brand">
        <Image src={dumbell} rounded height="40" width="40" style={{marginRight: 10}}/>
          Workouts
        </div>
        </nav>
        <DropdownButton id="dropdown-item-button" title="Show Recent Workouts">
          <Dropdown.ItemText>Select number of recent workouts to show:</Dropdown.ItemText>
          <Dropdown.Item as="button" onClick={()=>{getWorkouts(1)}}>1</Dropdown.Item>
          <Dropdown.Item as="button" onClick={()=>{getWorkouts(5)}}>5</Dropdown.Item>
          <Dropdown.Item as="button" onClick={()=>{getWorkouts(10)}}>10</Dropdown.Item>
          <Dropdown.Item as="button" onClick={()=>{getWorkouts(40)}}>All</Dropdown.Item>
        </DropdownButton>
        <Button variant="primary" onClick={()=>{setWorkoutList([]); setWoPageNum(1); setCurrentWoIndex(1);}}> Clear </Button>
        <Container>
          <Row>
            <Col>{workoutList[currentWoIndex - 1]}</Col>
            <Col>{workoutList[currentWoIndex]}</Col>
            <Col>{workoutList[currentWoIndex + 1]}</Col>
          </Row>
          <Row>
            <Col>{workoutList[currentWoIndex + 2]}</Col>
            <Col>{workoutList[currentWoIndex + 3]}</Col>
            <Col>{workoutList[currentWoIndex + 4]}</Col>
          </Row>
          <Row>
            <Pagination>{pagItems}</Pagination>
          </Row>
        </Container>
        <br />
        <br />
        <DatePicker selected={startDate} onChange={date => getWorkoutByDate(date)} />
        <br />
        <Button variant="primary" onClick={()=>setWorkoutByDate([])}> Clear </Button>
        {workoutByDate}
      </div>}

      {/* Movements page */}
      {currentPage === 'movements' && <div>
        <nav className="navbar navbar-light bg-light">
          <div className="navbar-brand">
          <Image src={movement} rounded height="40" width="40" style={{marginRight: 10}}/>
            Movements
          </div>
        </nav>
        <Button style={{marginLeft: 10}} variant="primary" onClick={()=>{getMovements()}}> Show Movements</Button>
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
      </div>}
    </div>
  );
}

export default App;
