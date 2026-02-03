export interface MovieDetailsType {
  id: number;
  name: string;
  duration: number;
  posterUrl: string;
}
export interface MovieSummaryType {
  id: number;
  name: string;
}

export interface CreateMovieDTO {
  name: string;
  duration: number;
  posterUrl: string;
}
export interface UpdateMovieDTO {
  id: number;
  name: string;
  duration: number;
  posterUrl: string;
}
