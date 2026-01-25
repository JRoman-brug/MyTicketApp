import { useQuery } from '@tanstack/react-query';
import { movieService } from '../services/movieService';
import { CANT_RETRY_TIME, movieKeys, STALE_TIME } from './keys';

export const useMovie = (movieId: number) => {
  const query = useQuery({
    queryKey: movieKeys.detail(movieId),
    queryFn: () => movieService.getMovie(movieId),
    staleTime: STALE_TIME,
    retry: CANT_RETRY_TIME,
  });
  return {
    movie: query.data,
    isError: query.isError,
    isLoading: query.isLoading,
    error: query.error,
  };
};
