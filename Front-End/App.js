import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Home from './components/home/Home';
import Header from './components/header/Header';
import Trailer from './components/trailer/Trailer';
import Reviews from './components/reviews/Reviews';
import NotFound from './components/notFound/NotFound';
import Login from './components/Login/Login';
import Register from './components/Register/Register';
import Watchlist from './components/WatchList/WatchList';

function App() {
  const [movies, setMovies] = useState([]);
  const [movie, setMovie] = useState();
  const [reviews, setReviews] = useState([]);

  const getMovies = async () => {
    try {
      const response = await api.get("/Movies?page=0&size=10");
      console.log("API Response:", response.data); // Log the full response
      if (response.data && Array.isArray(response.data.content)) {
        setMovies(response.data.content);
      } else {
        console.log("Invalid data format:", response.data);
        setMovies([]); // Default to empty array
      }
    } catch (err) {
      console.log("API Error:", err);
      setMovies([]); // Prevent crash on error
    }
  };

  const getMovieData = async (movieId) => {
    try {
      const response = await api.get(`/Movies/${movieId}`); // Updated to match your backend
      const singleMovie = response.data;
      setMovie(singleMovie);
      setReviews(singleMovie.reviews || []); // Default to empty array if no reviews
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getMovies();
  }, []);

  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="/" element={<Home movies={movies} />} />
          <Route path="/Trailer/:ytTrailerId" element={<Trailer />} />
          <Route path="/Reviews/:movieId" element={<Reviews getMovieData={getMovieData} movie={movie} reviews={reviews} setReviews={setReviews} />} />
          <Route path="/watchlist" element={<Watchlist />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;