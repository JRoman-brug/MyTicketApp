import { useQuery } from '@tanstack/react-query';
import { CANT_RETRY_TIME, showtimesKeys, STALE_TIME } from './showTimeKeys';
import { showtimeService } from '../services/showtimeService';

export const useShowtime = (showtimeId: number) => {
  const query = useQuery({
    queryKey: showtimesKeys.detail(showtimeId),
    queryFn: () => showtimeService.getShowtimeById(showtimeId),
    staleTime: STALE_TIME,
    retry: CANT_RETRY_TIME,
  });
  return {
    showtime: query.data,
    isError: query.isError,
    isLoading: query.isLoading,
    error: query.error,
  };
};
