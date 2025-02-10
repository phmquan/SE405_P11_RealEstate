import React, { useState, useEffect } from 'react';
import { Form, Button } from 'react-bootstrap';
import './login.scss';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  // Check if token exists when component mounts (using cookie for refresh_token)
  useEffect(() => {
    if (Cookies.get('refresh_token')) {
      navigate('/');
    }
  }, [navigate]);

  const validateForm = () => {
    const newErrors = {};

    if (!username) {
      newErrors.username = 'Username is required';
    } else if (!/\S+@\S+\.\S+/.test(username)) {
      newErrors.username = 'Username is invalid';
    }

    if (!password) {
      newErrors.password = 'Password is required';
    } else if (password.length < 6) {
      newErrors.password = 'Password must be at least 6 characters';
    }

    return newErrors;
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const formErrors = validateForm();
    if (Object.keys(formErrors).length > 0) {
      setErrors(formErrors);
      return;
    }

    setErrors({});
    console.log('Login attempted with:', { username, password });

    try {
      const response = await axios.post('http://localhost:8080/api/v1/auth/login', {
        username,
        password,
      });
      console.log('API response:', response);

      if (response.status === 200) {
        const { access_token, refresh_token } = response.data.data;

        // Store access_token in localStorage
        localStorage.setItem('access_token', access_token);

        // Store refresh_token in a cookie (expires in 7 days, adjust as needed)
        Cookies.set('refresh_token', refresh_token, { expires: 7, path: '/' });

        console.log('Navigating to /');
        navigate('/');
      } else {
        setErrors({ password: 'Login failed. Please try again.' });
      }
    } catch (error) {
      console.error('Login error:', error);
      setErrors({ password: 'Invalid username or password' });
    }
  };

  return (
    <div className="login-wrapper">
      <div className="login-form-container">
        <h2 className="login-title">Login</h2>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3" controlId="formBasicUsername">
            <Form.Label>Email address</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            {errors.username && <div className="error">{errors.username}</div>}
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {errors.password && <div className="error">{errors.password}</div>}
          </Form.Group>

          <Button variant="primary" type="submit" className="login-button">
            Login
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default Login;
