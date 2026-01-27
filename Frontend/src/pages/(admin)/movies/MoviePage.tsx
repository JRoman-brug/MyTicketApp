import MoviePagination from '@/features/movies/components/MoviePagination';
import MovieTable from '@/features/movies/components/MovieTable';
import { useAllMovies } from '@/features/movies/hooks/useAllMovies';
import { useState } from 'react';

function MoviePage() {
  const [currentPage, setCurrentPage] = useState(1);
  const { movies } = useAllMovies(currentPage - 1, 5);
  if (!movies) return <div>no hay</div>;
  return (
    <div className="h-screen flex flex-col items-center p-4 gap-4">
      <MovieTable movies={movies?.content} />
      <MoviePagination
        currentPage={currentPage}
        setCurrentPage={(page: number) => setCurrentPage(page)}
        totalPage={movies?.totalPages}
      />
    </div>
  );
}

export default MoviePage;
