import type { RouteObject } from 'react-router-dom';
import ShowtimePage from '@/pages/(admin)/showtimes/ShowtimePage';
import UpdateShowtimePage from './UpdateShowtimePage';
// Exportamos un array de objetos ruta
export const showtimeRoutes: RouteObject[] = [
  {
    path: 'showtimes',
    children: [
      {
        index: true,
        element: <ShowtimePage />,
      },
      {
        path: 'create',
        // element: <CreateMoviePage />,
      },
      {
        path: 'edit/:id',
        element: <UpdateShowtimePage />,
      },
    ],
  },
];
