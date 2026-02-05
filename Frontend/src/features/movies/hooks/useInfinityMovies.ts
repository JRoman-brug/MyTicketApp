import { useInfiniteQuery } from '@tanstack/react-query';
import { movieService } from '../services/movieService';

export const useInfiniteMovies = () => {
  const query = useInfiniteQuery({
    queryKey: ['movies-infinite'],
    // pageParam es el número de página actual que gestiona React Query
    queryFn: ({ pageParam = 0 }) => movieService.getAllMovies(pageParam, 8),
    initialPageParam: 0,
    getNextPageParam: (lastPage) => {
      const nextPage = lastPage.pageNumber + 1;
      if (nextPage < lastPage.totalPages) {
        return nextPage;
      }
    },
    select: (data) => {
      const allMovies = data.pages.flatMap((page) => page.content);
      return {
        options: allMovies,
        pageParams: data.pageParams,
      };
    },
  });
  return {
    movies: query.data,
    hasMore: query.hasNextPage,
    isLoading: query.isLoading,
    onLoadMore: query.fetchNextPage,
  };
};
