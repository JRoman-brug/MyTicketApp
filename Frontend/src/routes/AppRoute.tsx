import CreateMoviePage from '@/pages/(admin)/movies/createMovie/CreateMoviePage';
import EditMoviePage from '@/pages/(admin)/movies/editMovie/EditMoviePage';
import MoviePage from '@/pages/(admin)/movies/MoviePage';
import { showtimeRoutes } from '@/pages/(admin)/showtimes/showtimeRoute';
import RootLayout from '@/pages/RootLayout';
import { createBrowserRouter } from 'react-router-dom';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <RootLayout />,
    children: [
      ...showtimeRoutes,
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
          {
            path: 'edit/:id',
            element: <EditMoviePage />,
          },
        ],
      },
    ],
  },
]);
