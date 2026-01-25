import { api } from '@/lib/axios';
import type { CreateMovieDTO, UpdateMovieDTO } from '../types/movieType';

export const movieService = {
  getMovie: async (movieId: number) => {
    const { data } = await api.get(`/movies/${movieId}`);
    return data;
  },

  createMovie: async (newMovie: CreateMovieDTO) => {
    const { data } = await api.post(`/movies`, newMovie);
    return data;
  },

  updateMovie: async (movie: UpdateMovieDTO) => {
    const { data } = await api.patch(`/movies`, movie);
    return data;
  },
  deleteMovie: async (movieId: number) => {
    const { data } = await api.delete(`/movies/${movieId}`);
    return data;
  },
};
