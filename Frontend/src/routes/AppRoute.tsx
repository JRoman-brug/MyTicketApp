import MoviePage from '@/pages/(admin)/movies/MoviePage';
import RootLayout from '@/pages/RootLayout';
import { createBrowserRouter } from 'react-router-dom';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <RootLayout />, // <--- AQUI APLICAS EL LAYOUT
    children: [
      {
        path: '/movies',
        element: <MoviePage />,
      },
      // ... el resto de tus rutas
    ],
  },
]);
