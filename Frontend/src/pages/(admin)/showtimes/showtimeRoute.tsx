import type { RouteObject } from 'react-router-dom';
import ShowtimePage from '@/pages/(admin)/showtimes/ShowtimePage';
import UpdateShowtimePage from './UpdateShowtimePage';
import CreateShowtimePage from './CreateShowtimePage/CreateShowtimePage';
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
        element: <CreateShowtimePage />,
      },
      {
        path: 'edit/:id',
        element: <UpdateShowtimePage />,
      },
    ],
  },
];
