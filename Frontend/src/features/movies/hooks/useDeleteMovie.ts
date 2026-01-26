import { useMutation, useQueryClient } from '@tanstack/react-query';
import { movieService } from '../services/movieService';
import { movieKeys } from './keys';

export const useDeleteMovie = () => {
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: (id: number) => movieService.deleteMovie(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: movieKeys.lists() });
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
