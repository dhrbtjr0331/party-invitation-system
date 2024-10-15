import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleLogin = () => {
    axios.post('http://localhost:8080/api/login', { username, password })
      .then(response => {
        console.log('Login successful');
        navigate('/create-party'); // Redirect to create party page
      })
      .catch(error => {
        setErrorMessage('Login failed: ' + error.message);
      });
  };

  return (
    <div>
      <h2>Login</h2>
      <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
      <p>{errorMessage}</p>
    </div>
  );
}

export default Login;
