import type { RouteObject } from 'react-router-dom';

// Exportamos un array de objetos ruta
export const showtimeRoutes: RouteObject[] = [
  {
    path: 'showtime',
    children: [
      {
        index: true,
        // element: <MoviePage />,
      },
      {
        path: 'create',
        // element: <CreateMoviePage />,
      },
      {
        path: 'edit/:id',
        // element: <EditMoviePage />,
      },
    ],
  },
];
