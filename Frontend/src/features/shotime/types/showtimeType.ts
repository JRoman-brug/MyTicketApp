import type { HallDetailsType } from '@/features/cinema/types';
import type { MovieSummaryType } from '@/features/movies/types/movieType';

export interface CreateRequest {
  startTime: string;
  movieId: number;
  hallId: number;
}

export interface UpdateRequest {
  id: number;
  startTime: string;
  hallId: string;
}

export interface Response {
  startTime: string;
  movie: MovieSummaryType;
  hall: HallDetailsType;
  totalSeats: number;
  availableSeats: number;
}
export interface Summary {
  id: number;
  startTime: string;
  movieTitle: string;
  hallName: string;
}
