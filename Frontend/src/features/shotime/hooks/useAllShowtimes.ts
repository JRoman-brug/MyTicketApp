import { keepPreviousData, useQuery } from '@tanstack/react-query';
import { showtimesKeys, STALE_TIME, CANT_RETRY_TIME } from './showTimeKeys';
import { showtimeService } from '../services/showtimeService';

export const useAllMovies = (page: number, size = 10) => {
  const query = useQuery({
    queryKey: [...showtimesKeys.lists(), { page, size }],
    queryFn: () => showtimeService.getAllShowtime(page, size),
    placeholderData: keepPreviousData,
    staleTime: STALE_TIME,
    retry: CANT_RETRY_TIME,
  });
  return {
    movies: query.data,
    isLoading: query.isLoading,
    isError: query.isError,
    error: query.error,
  };
};
