import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const [parties, setParties] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  // Fetch all parties from the backend
  useEffect(() => {
    axios.get('http://localhost:8080/api/parties')
      .then((response) => {
        setParties(response.data); // Set the parties data
      })
      .catch((err) => {
        setError('Failed to load parties: ' + err.message); // Handle errors
      });
  }, []);

  // Handle invite button click
  const handleInvite = (partyId) => {
    navigate(`/invite/${partyId}`); // Redirect to the invite page for the selected party
  };

  return (
    <div>
      <h2>All Parties</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}

      {parties.length > 0 ? (
        <table border="1" cellPadding="10">
          <thead>
            <tr>
              <th>Party Name</th>
              <th>Date and Time</th>
              <th>Location</th>
              <th>Host</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {parties.map((party) => (
              <tr key={party.id}>
                <td>{party.name}</td>
                <td>{new Date(party.dateTime).toLocaleString()}</td>
                <td>{party.location}</td>
                <td>{party.host ? party.host.username : 'Unknown Host'}</td>
                <td>
                  <button onClick={() => handleInvite(party.id)}>
                    Invite
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No parties found.</p>
      )}
    </div>
  );
}

export default Dashboard;
