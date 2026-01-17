import { useMutation } from '@tanstack/react-query';
import { cinemaService } from '../services/cinemaService';
import { queryClient } from '@/lib/react-query';
import { cinemaKeys } from './useCinema';

export const seatKeys = {
  all: ['seat'] as const,
  seat: (id: string) => [...seatKeys.all, 'seat', id] as const,
};

export const useSeat = (hallId: string) => {
  const reserveSeatMutation = useMutation({
    mutationFn: cinemaService.reserveSeatById,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: cinemaKeys.hall(hallId),
      });
    },
    onError: () => {
      console.log('Error');
    },
  });

  const releaseSeatMutation = useMutation({
    mutationFn: cinemaService.releaseSeatById,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: cinemaKeys.hall(hallId),
      });
    },
    onError: () => {
      console.log('Error');
    },
  });

  return {
    //actions
    reserveSeat: reserveSeatMutation.mutate,
    releaseSeat: releaseSeatMutation.mutate,
    isModifying: reserveSeatMutation.isPending || releaseSeatMutation.isPending,
  };
};
