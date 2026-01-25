export interface MovieType {
  id: number;
  name: string;
  duration: number;
  posterUrl: string;
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
