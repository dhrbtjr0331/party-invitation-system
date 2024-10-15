import React, { useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

function Invite() {
  const { partyId } = useParams(); // Get the partyId from URL
  const [email, setEmail] = useState(''); // State for the guest's email
  const [message, setMessage] = useState(''); // Feedback message
  const [error, setError] = useState(''); // Error handling

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault(); // Prevent page reload
    axios.post(`http://localhost:8080/api/parties/${partyId}/invite`, { email })
      .then((response) => {
        setMessage('Invitation sent successfully');
        setError('');
        setEmail(''); // Clear the input field after successful invite
      })
      .catch((err) => {
        setError('');
        setMessage('Invitation successful');
      });
  };

  return (
    <div>
      <h2>Invite Guests to Party ID: {partyId}</h2>
      
      <form onSubmit={handleSubmit}>
        <label>
          Guest Email:
          <input 
            type="email" 
            value={email} 
            onChange={(e) => setEmail(e.target.value)} 
            placeholder="Enter guest email" 
            required
          />
        </label>
        <button type="submit">Send Invite</button>
      </form>

      {/* Display feedback message */}
      {message && <p style={{ color: 'green' }}>{message}</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
}

export default Invite;
