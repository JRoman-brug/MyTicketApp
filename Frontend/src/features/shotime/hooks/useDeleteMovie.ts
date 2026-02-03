import { useMutation, useQueryClient } from '@tanstack/react-query';
import { showtimeService } from '../services/showtimeService';
import { showtimesKeys } from './showTimeKeys';

export const useDeleteMovie = () => {
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: (showtimeId: number) =>
      showtimeService.deleteShowtime(showtimeId),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: showtimesKeys.lists() });
      console.log('Movies deleted succesfully');
    },
    onError: (error) => {
      console.log(error);
    },
  });

  return {
    deleteMovie: mutation.mutate,
    isPending: mutation.isPending,
    isError: mutation.isError,
  };
};
