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

const WorkoutPage = (props) => {
    const [workoutList, setWorkoutList] = useState([]);
    const [currentWoIndex, setCurrentWoIndex] = useState(1);
    const [pagItems, setPagItems] = useState([]);
    const [woPageNum, setWoPageNum] = useState(1);
    const [startDate, setStartDate] = useState(new Date());
    const [workoutByDate, setWorkoutByDate] = useState(<MovementCard></MovementCard>);

    const getWorkouts = (num) => {
      console.log(props.token);
        Axios.get("https://progress-pal-front.herokuapp.com/api/v1/workouts/recent/user/"+props.userId+"/"+num, {
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
        Axios.get("https://progress-pal-front.herokuapp.com/api/v1/workouts/user/bydate/"+props.userId, {
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
      </div>;
}

export default WorkoutPage;