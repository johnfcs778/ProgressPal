import React, { useState } from 'react';
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { useNavigate } from "react-router-dom";
import {Alert} from "react-bootstrap";
import Axios from 'axios';
import "./Register.css";

const RegisterPage = () => {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [firstname, setFirstName] = useState("");
    const [lastname, setLastName] = useState("");
    const navigate = useNavigate();


    function validateForm() {
        return email.length > 0 && password.length > 0 &&  firstname.length > 0 &&  lastname.length > 0;
      }
    
      function handleSubmit(event) {
        login(event.target[0].value, event.target[1].value);
        event.preventDefault();
      }

      const login = (username, pw) => {
    
        Axios.post("https://progress-pal-front.herokuapp.com/api/v1/registration", {
            firstName: firstname,
            lastName : lastname,
            email : email,
            password : password
        }).then((response)=> {
            if(response['status'] === 200) {
              navigate('/login');
            } else {
              // show error
            }
            console.log(response);
        })
      }

    return (
        <div className="Login">
          <Form onSubmit={handleSubmit}>
            <Form.Group size="lg" controlId="email">
                <h2 style={{marginBottom: 20}} > Signup </h2>
                <Form.Label><b>First Name</b></Form.Label>
                <Form.Control
                    style={{marginBottom: 20}}
                    autoFocus
                    type="text"
                    value={firstname}
                    onChange={(e) => setFirstName(e.target.value)}
                    placeholder="First Name"
                />
                <Form.Label><b>Last Name</b></Form.Label>
                <Form.Control
                    style={{marginBottom: 20}}
                    autoFocus  
                    type="text"
                    value={lastname}
                    placeholder="Last Name"
                    onChange={(e) => setLastName(e.target.value)}
                />
                <Form.Label><b>Email</b></Form.Label>
                <Form.Control
                    style={{marginBottom: 20}}
                    autoFocus
                    type="email"
                    value={email}
                    placeholder="example@example.com"
                    onChange={(e) => setEmail(e.target.value)}
                />
                <Form.Label><b>Password</b></Form.Label>
                <Form.Control
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </Form.Group>
            <Button block size="lg" type="submit" disabled={!validateForm()}>
              Register
            </Button>
    
            <Alert  style={{marginTop: 20}} variant="info">
              Already registered? 
              <Alert.Link href="/login"> Sign in.</Alert.Link>
            </Alert>
          </Form>
        </div>
      );
}

export default RegisterPage;