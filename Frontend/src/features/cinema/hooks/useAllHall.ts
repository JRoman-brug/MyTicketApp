import { useQuery } from '@tanstack/react-query';
import { cinemaKeys } from './useCinema';
import { cinemaService } from '../services/cinemaService';
import { CANT_RETRY_TIME, STALE_TIME } from '@/features/movies/hooks/keys';

export const useAllHall = () => {
  const query = useQuery({
    queryKey: [...cinemaKeys.all],
    queryFn: cinemaService.getAllHalls,
    staleTime: STALE_TIME,
    retry: CANT_RETRY_TIME,
  });
  return {
    halls: query.data,
    isLoading: query.isLoading,
    isError: query.isError,
    error: query.error,
  };
};
