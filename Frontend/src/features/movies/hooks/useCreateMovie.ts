import { movieKeys } from '@/features/movies/hooks/keys';
import { movieService } from '@/features/movies/services/movieService';
import type { CreateMovieDTO } from '@/features/movies/types/movieType';
import { useMutation, useQueryClient } from '@tanstack/react-query';

export const useCreateMovie = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (data: CreateMovieDTO) => movieService.createMovie(data),
    onSuccess: () => {
      console.log('Movie created successfully.');
      queryClient.invalidateQueries({ queryKey: movieKeys.lists() });
      //   Navigate
    },
    onError: (error) => {
      console.error(error);
    },
  });
};
