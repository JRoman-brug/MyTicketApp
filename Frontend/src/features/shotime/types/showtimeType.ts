import type { HallDetailsType } from '@/features/cinema/types';
import type { MovieSummaryType } from '@/features/movies/types/movieType';

export interface CreateShowtimeType {
  startTime: string;
  movieId: number;
  hallId: number;
}

export interface UpdateShowtimeType {
  id: number;
  startTime?: string;
  hallId?: string;
}

export interface ShowtimeDetailsType {
  id: number;
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
