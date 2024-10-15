import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleRegister = () => {
    axios.post('http://localhost:8080/api/users', { username, password, email })
      .then(response => {
        setMessage('User registered successfully');
        navigate('/login'); // Redirect to login after registration
      })
      .catch(error => {
        setMessage('Error: ' + error.message);
      });
  };

  return (
    <div>
      <h2>Register</h2>
      <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
      <button onClick={handleRegister}>Register</button>
      <p>{message}</p>
    </div>
  );
}

export default Register;
