import React from 'react';
import { Container, ListGroup } from 'react-bootstrap';

const Watchlist = () => {
  // Mock data for now (later we'll fetch from backend or user state)
  const mockWatchlist = [
    { id: 1, title: 'Inception', imdbId: 'tt1375666' },
    { id: 2, title: 'The Dark Knight', imdbId: 'tt0468569' },
    { id: 3, title: 'Interstellar', imdbId: 'tt0816692' },
  ];

  return (
    <Container style={{ padding: '20px' }}>
      <h2>My Watchlist</h2>
      {mockWatchlist.length > 0 ? (
        <ListGroup>
          {mockWatchlist.map((item) => (
            <ListGroup.Item key={item.id}>
              {item.title} (IMDb: {item.imdbId})
            </ListGroup.Item>
          ))}
        </ListGroup>
      ) : (
        <p>No movies in your watchlist yet.</p>
      )}
    </Container>
  );
};

export default Watchlist;