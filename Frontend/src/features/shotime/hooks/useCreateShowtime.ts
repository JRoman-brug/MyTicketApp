import { useMutation, useQueryClient } from '@tanstack/react-query';
import type { CreateShowtimeType } from '../types/showtimeType';
import { showtimeService } from '../services/showtimeService';
import { showtimesKeys } from './showTimeKeys';

export const useCreateMovie = () => {
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: (data: CreateShowtimeType) =>
      showtimeService.createShowtime(data),
    onSuccess: () => {
      console.log('Movie created successfully.');
      queryClient.invalidateQueries({ queryKey: showtimesKeys.lists() });
      //   Navigate
    },
    onError: (error) => {
      console.error(error);
    },
  });
  return {
    createMovie: mutation.mutate,
    isPending: mutation.isPending,
    isError: mutation.isError,
    error: mutation.error,
  };
};
