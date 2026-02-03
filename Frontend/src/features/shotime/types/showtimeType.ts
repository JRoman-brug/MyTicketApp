import type { HallDetailsType } from '@/features/cinema/types';
import type { MovieSummaryType } from '@/features/movies/types/movieType';

export interface CreateRequestType {
  startTime: string;
  movieId: number;
  hallId: number;
}

export interface UpdateRequestType {
  id: number;
  startTime: string;
  hallId: string;
}

export interface ShowtimeDetailsType {
  startTime: string;
  movie: MovieSummaryType;
  hall: HallDetailsType;
  totalSeats: number;
  availableSeats: number;
}
export interface ShowtimeSummaryType {
  id: number;
  startTime: string;
  movieTitle: string;
  hallName: string;
}
