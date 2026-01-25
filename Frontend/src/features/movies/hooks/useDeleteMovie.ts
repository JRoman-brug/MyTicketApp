import { useMutation, useQueryClient } from '@tanstack/react-query';
import { movieService } from '../services/movieService';
import { movieKeys } from './keys';

export const useDeleteMovie = () => {
  const queryClient = useQueryClient();
  useMutation({
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
    deleteMovie: useMutation,
  };
};
