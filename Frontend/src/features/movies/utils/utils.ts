import type { MovieSummaryType } from '../types/movieType';

// TODO remove this when backend implement a endpoint to get movies
export const movies: MovieSummaryType[] = [
  {
    id: 1,
    name: 'Oppenheimer',
    duration: 180,
    posterUrl:
      'https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg',
  },
  {
    id: 2,
    name: 'Spider-Man: Across the Spider-Verse',
    duration: 140,
    posterUrl:
      'https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg',
  },
  {
    id: 3,
    name: 'Barbie',
    duration: 114,
    posterUrl:
      'https://image.tmdb.org/t/p/w500/iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg',
  },
  {
    id: 4,
    name: 'Avatar: The Way of Water',
    duration: 192,
    posterUrl:
      'https://image.tmdb.org/t/p/w500/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg',
  },
  {
    id: 5,
    name: 'The Super Mario Bros. Movie',
    duration: 92,
    posterUrl:
      'https://image.tmdb.org/t/p/w500/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg',
  },
];
