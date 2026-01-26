import { keepPreviousData, useQuery } from '@tanstack/react-query';
import { movieKeys, STALE_TIME } from './keys';
import { movieService } from '../services/movieService';

export const useAllMovies = (page: number, size = 10) => {
  const query = useQuery({
    queryKey: [...movieKeys.lists(), { page, size }],
    queryFn: () => movieService.getAllMovies(page, size),
    placeholderData: keepPreviousData,
    staleTime: STALE_TIME,
  });
  return {
    movies: query.data,
    isLoading: query.isLoading,
    isError: query.isError,
    error: query.error,
  };
};
