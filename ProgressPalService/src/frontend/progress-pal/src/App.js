import MovementPage from './Movements/MovementPage';
import WorkoutPage from './Workouts/WorkoutPage';
import './App.css';
import React, {useState, createContext} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import { Nav } from 'react-bootstrap';
import "react-datepicker/dist/react-datepicker.css";
import Homepage from './Homepage';
import LoginPage from './Login/LoginPage';
import {Route, Routes, useNavigate, useLocation, BrowserRouter as Router} from 'react-router-dom';
import Login from './Login/LoginPage';
import ErrorPage from './Error/ErrorPage';
import Footer from './Footer/Footer';
import RegisterPage from './Register/RegisterPage';

function App() {

  const [currentPage, setCurrentPage] = useState("workouts");
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <React.Fragment>
       {location.pathname != '/login' && location.pathname != '/register' && <Nav className="justify-content-center">
        <Nav.Item>
          <Nav.Link  as="button" onClick={()=>{navigate('/')}}>Home</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link as="button" onClick={()=>{navigate('/workouts')}}>Workouts</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link as="button" onClick={()=>{navigate('/movements')}}>Movements</Nav.Link>
        </Nav.Item>
      </Nav>}
      <Routes>
 
        {/* Login Page */}
        <Route path='/login' element={<LoginPage />} />
        {/* Home Page */}
        <Route path='/' element={<Homepage/>} />
        {/* Error Page */}
        <Route path='/*' element={<ErrorPage/>} />
        {/* Workouts Page */}
        <Route path='/workouts' element={<WorkoutPage/>} />
        {/* Movements Page */}
        <Route path='/movements' element={<MovementPage/>} />
        {/* Login Page */}
        <Route path='/login' element={<LoginPage />} />
        {/* Register Page */}
        <Route path='/register' element={<RegisterPage />} />
      </Routes>
      <Footer />
    </React.Fragment>
  );
}

export default App;
