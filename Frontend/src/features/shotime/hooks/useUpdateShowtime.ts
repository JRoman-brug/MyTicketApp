import { useMutation, useQueryClient } from '@tanstack/react-query';
import type { UpdateShowtimeType } from '../types/showtimeType';
import { showtimeService } from '../services/showtimeService';
import { showtimesKeys } from './showTimeKeys';

export const useUpdateShowtime = () => {
  const queryClient = useQueryClient();
  const mutation = useMutation({
    mutationFn: (data: UpdateShowtimeType) =>
      showtimeService.updateShowtime(data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: showtimesKeys.lists() });
      console.log('Showtime updated successfuly');
    },
    onError: (error) => {
      console.log(error);
    },
  });
  return {
    updateShowtime: mutation.mutate,
    isPending: mutation.isPending,
    isError: mutation.isError,
    error: mutation.error,
  };
};
