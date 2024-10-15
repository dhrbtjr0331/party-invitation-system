import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function CreateParty() {
  const [partyName, setPartyName] = useState('');
  const [partyDateTime, setPartyDateTime] = useState('');
  const [location, setLocation] = useState('');
  const [isPublic, setIsPublic] = useState(false);
  const [hostName, setHostName] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleCreateParty = () => {
    axios.post('http://localhost:8080/api/parties', {
      name: partyName,
      dateTime: partyDateTime,
      location: location,
      isPublic: isPublic,
      host: { username: hostName }
    })
      .then(response => {
        setMessage('Party created successfully');
        navigate('/dashboard'); // Redirect to the dashboard after party creation
      })
      .catch(error => {
        setMessage('Error: ' + error.message);
      });
  };

  return (
    <div>
      <h2>Create Party</h2>
      <div>
        <label>Host Username:</label>
        <input 
            type="text" 
            placeholder="Host Username" 
            value={hostName} 
            onChange={(e) => setHostName(e.target.value)} 
        />
      </div>
      <div>
        <label>Party Name:</label>
        <input 
          type="text" 
          placeholder="Party Name" 
          value={partyName} 
          onChange={(e) => setPartyName(e.target.value)} 
        />
      </div>
      <div>
        <label>Date and Time:</label>
        <input 
          type="datetime-local" 
          value={partyDateTime} 
          onChange={(e) => setPartyDateTime(e.target.value)} 
        />
      </div>
      <div>
        <label>Location:</label>
        <input 
          type="text" 
          placeholder="Location" 
          value={location} 
          onChange={(e) => setLocation(e.target.value)} 
        />
      </div>
      <div>
        <label>Is Public:</label>
        <input 
          type="checkbox" 
          checked={isPublic} 
          onChange={(e) => setIsPublic(e.target.checked)} 
        />
      </div>
      <button onClick={handleCreateParty}>Create Party</button>
      <p>{message}</p>
    </div>
  );
}

export default CreateParty;

