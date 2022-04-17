import { MovementPage } from './Movements/MovementPage';
import { WorkoutPage } from './Workouts/WorkoutPage';
import './App.css';
import React, {useState, createContext} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import { Nav } from 'react-bootstrap';
import "react-datepicker/dist/react-datepicker.css";
import Homepage from './Homepage';
import LoginPage from './Login/LoginPage';
import {Route, BrowserRouter as Router} from 'react-router-dom';

function App() {

  const [currentPage, setCurrentPage] = useState("workouts");

  return (
    <Router>
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
      {currentPage === 'workouts' && <WorkoutPage />}

      {/* Movements page */}
      {currentPage === 'movements' && <MovementPage />}
    </Router>
  );
}

export default App;
