import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Register from './Register';
import Login from './Login';
import CreateParty from './CreateParty';
import Dashboard from './Dashboard';
import Invite from './Invite';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/create-party" element={<CreateParty />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/invite/:partyId" element={<Invite />} />
      </Routes>
    </Router>
  );
}

export default App;

