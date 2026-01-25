import { useMutation, useQueryClient } from '@tanstack/react-query';
import type { UpdateMovieDTO } from '../types/movieType';
import { movieService } from '../services/movieService';
import { movieKeys } from './keys';

export const useUpdateMovie = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (data: UpdateMovieDTO) => movieService.updateMovie(data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: movieKeys.lists() });
      console.log('Movie updated successfuly');
    },
    onError: (error) => {
      console.log(error);
    },
  });
};
