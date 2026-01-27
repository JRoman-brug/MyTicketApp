import CreateMoviePage from '@/pages/(admin)/movies/createMovie/CreateMoviePage';
import MoviePage from '@/pages/(admin)/movies/MoviePage';
import RootLayout from '@/pages/RootLayout';
import { createBrowserRouter } from 'react-router-dom';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <RootLayout />,
    children: [
      {
        path: 'movies',
        children: [
          {
            index: true,
            element: <MoviePage />,
          },
          {
            path: 'create',
            element: <CreateMoviePage />,
          },
        ],
      },
    ],
  },
]);
