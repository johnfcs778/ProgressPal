import Axios from 'axios';
import Button from 'react-bootstrap/Button';
import React, {useState, createContext} from 'react';
import WorkoutCard from './WorkoutCard';
import MovementCard from '../Movements/MovementCard';
import 'bootstrap/dist/css/bootstrap.css';
import { Dropdown, DropdownButton, Nav, Container, Image, Col, Row, Pagination } from 'react-bootstrap';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import dumbell from '../dumbell.png';
import Popup from "reactjs-popup";
import Form from "react-bootstrap/Form";

const WorkoutPage = (props) => {
    const [workoutList, setWorkoutList] = useState([]);
    const [currentWoIndex, setCurrentWoIndex] = useState(1);
    const [pagItems, setPagItems] = useState([]);
    const [woPageNum, setWoPageNum] = useState(1);
    const [startDate, setStartDate] = useState(new Date());
    const [workoutByDate, setWorkoutByDate] = useState(<MovementCard></MovementCard>);

    const getWorkouts = (num) => {
      console.log(props.token);
        Axios.get("http://localhost:8080/api/v1/workouts/recent/user/"+props.userId+"/"+num, {
          headers: {
            'Authorization': `Bearer ${props.token}` 
          }
        }).then((response)=> {
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

      const getWorkoutByDate = (date) => {
        setStartDate(date);
        Axios.get("http://localhost:8080/api/v1/workouts/user/bydate/"+props.userId, {
          headers: {
            'Authorization': `Bearer ${props.token}` 
          },
          params: {
            year: date.getFullYear(),
            day: date.getDate(),
            month: date.getMonth() + 1
          }
        }).then((response)=> {
          setWorkoutByDate(<WorkoutCard workoutData={response.data[0]}></WorkoutCard>)
        })
      }

      const addWorkout = (workoutType, date, length, notes, milestoneReached) => {
        Axios.post(
          "http://localhost:8080/api/v1/workouts/user/" + props.userId,
          {
            workoutType: workoutType,
            date: date,
            length: length,
            notes: notes,
            milestoneReached: milestoneReached === 'on' ? true : false,
          },
          {
            headers: {
              Authorization: `Bearer ${props.token}`,
            },
          }
        ).then((response) => {
          if (response["status"] === 200) {
            //getWorkouts();
          } else {
            // show error
          }
          console.log(response);
        });
      };
    
      function handleSubmit(event) {
        addWorkout(
          event.target[0].value,
          event.target[1].value,
          event.target[2].value,
          event.target[3].value,
          event.target[4].value
        );
        event.preventDefault();
        document.getElementById("closebutton").click();
      }
      
      React.useEffect(()=> {
        renderPagItems(workoutList.length);
      },[woPageNum, workoutList]);

  return <div className="justify-content-center">
        <nav className="navbar navbar-light bg-light">
        <div className="navbar-brand">
        <Image src={dumbell} rounded height="40" width="40" style={{
          marginRight: 10
        }} />
          Workouts
        </div>
        </nav>
        <DropdownButton id="dropdown-item-button" title="Show Recent Workouts">
          <Dropdown.ItemText>Select number of recent workouts to show:</Dropdown.ItemText>
          <Dropdown.Item as="button" onClick={() => {
        getWorkouts(1);
      }}>1</Dropdown.Item>
          <Dropdown.Item as="button" onClick={() => {
        getWorkouts(5);
      }}>5</Dropdown.Item>
          <Dropdown.Item as="button" onClick={() => {
        getWorkouts(10);
      }}>10</Dropdown.Item>
          <Dropdown.Item as="button" onClick={() => {
        getWorkouts(40);
      }}>All</Dropdown.Item>
        </DropdownButton>
        <Button variant="primary" onClick={() => {
      setWorkoutList([]);
      setWoPageNum(1);
      setCurrentWoIndex(1);
    }}> Clear </Button>
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
        <Button variant="primary" onClick={() => setWorkoutByDate([])}> Clear </Button>
        {workoutByDate}
        <Popup
        trigger={<Button>Add New Workout</Button>}
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
              <Form.Group size="lg" controlId="workout">
                <h2 style={{ marginBottom: 20 }}> Add a new Workout </h2>
                <Form.Label>
                  <b>Workout Type</b>
                </Form.Label>
                <Form.Control
                  style={{ marginBottom: 20 }}
                  autoFocus
                  type="text"
                  placeholder="Type"
                />
                <Form.Label>
                  <b>Date</b>
                </Form.Label>
                <Form.Control
                  style={{ marginBottom: 20 }}
                  autoFocus
                  type="date"
                />
                <Form.Label>
                  <b>Length</b>
                </Form.Label>
                <Form.Control
                  style={{ marginBottom: 20 }}
                  autoFocus
                  type="number"
                  placeholder={0}
                />
                <Form.Label>
                  <b>Notes</b>
                </Form.Label>
                <Form.Control
                  style={{ marginBottom: 20 }}
                  autoFocus
                  type="text"
                  placeholder=""
                />
                <Form.Label>
                  <b>Milestone Reached?</b>
                </Form.Label>
                <Form.Control
                  style={{ marginBottom: 20 }}
                  autoFocus
                  type="checkbox"
                />
              </Form.Group>
              <Button block size="lg" type="submit">
                Add
              </Button>
            </Form>
          </div>
        )}
      </Popup>
      </div>;
}

export default WorkoutPage;