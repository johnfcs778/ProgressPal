import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import {Alert} from "react-bootstrap";
import Axios from 'axios';
import { useNavigate } from "react-router-dom";
import PropTypes from 'prop-types';
import "./Login.css";

export default function Login({ setToken, setUserId }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  function validateForm() {
    return email.length > 0 && password.length > 0;
  }

  function handleSubmit(event) {
    login(event.target[0].value, event.target[1].value);
    event.preventDefault();
  }

  const login = () => {
    const params = new URLSearchParams();
    params.append('email', email);
    params.append('password', password);

    Axios.post("http://progress-pal-front.herokuapp.com/api/v1/login", params).then((response)=> {
        if(response['status'] === 200) {
          if(response['data']['access_token'] !== undefined) {
            setToken(response['data']['access_token']);
            setUserId(response['data']['userId']);
          }
          navigate('/');
        } else {
          // show error
        }
        console.log(response);
        //console.log(response['data']['access_token']);
    })
  }

  return (
    <div className="Login">
      <Form onSubmit={handleSubmit}>
        <Form.Group size="lg" controlId="email">
        <h2 style={{marginBottom: 20}} >Login to ProgressPal </h2>
          <Form.Label>Email</Form.Label>
          <Form.Control
            autoFocus
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="password">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
        <Button block size="lg" type="submit" disabled={!validateForm()}>
          Login
        </Button>

        <Alert  style={{marginTop: 20}} variant="info">
          Don't have an account? 
          <Alert.Link href="/register"> Register.</Alert.Link>
        </Alert>
      </Form>
    </div>
  );
}

Login.propTypes = {
  setToken: PropTypes.func.isRequired,
  setUserId: PropTypes.func.isRequired
};