import { useInfiniteQuery } from '@tanstack/react-query';
import { movieService } from '../services/movieService';

export const useInfiniteMovies = () => {
  const query = useInfiniteQuery({
    queryKey: ['movies-infinite'],
    // pageParam es el número de página actual que gestiona React Query
    queryFn: ({ pageParam = 0 }) => movieService.getAllMovies(pageParam, 10), // Tamaño 10
    initialPageParam: 0,
    getNextPageParam: (lastPage) => {
      // Si no es la última página, la siguiente es current + 1
      return lastPage.nextPath ? undefined : lastPage.pageNumber + 1;
    },
  });
  return {
    movies: query.data,
    hasMore: query.hasNextPage,
    isLoading: query.isLoading,
    onLoadMore: query.isFetching,
  };
};
