import { QueryClient } from '@tanstack/react-query';

export const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1, // Si falla, reintenta 1 vez
      refetchOnWindowFocus: false, // No recargar si cambio de pestaña
      staleTime: 1000 * 60 * 5, // Los datos duran frescos 5 minutos
    },
  },
});
