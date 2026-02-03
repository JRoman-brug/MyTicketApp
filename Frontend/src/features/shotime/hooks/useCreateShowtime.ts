import { useMutation, useQueryClient } from '@tanstack/react-query';
import type { CreateShowtimeType } from '../types/showtimeType';
import { showtimeService } from '../services/showtimeService';
import { showtimesKeys } from './showTimeKeys';

export const useCreateShowtime = () => {
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: (data: CreateShowtimeType) =>
      showtimeService.createShowtime(data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: showtimesKeys.lists() });
      console.log('Showtime created successfully.');
      //   Navigate
    },
    onError: (error) => {
      console.error(error);
    },
  });
  return {
    createShowtime: mutation.mutate,
    isPending: mutation.isPending,
    isError: mutation.isError,
    error: mutation.error,
  };
};
