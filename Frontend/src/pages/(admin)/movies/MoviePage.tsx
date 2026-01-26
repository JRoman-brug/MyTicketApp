import MovieTable from '@/features/movies/components/MovieTable';
import { useAllMovies } from '@/features/movies/hooks/useAllMovies';
import { Button } from '@heroui/react';
import { useState } from 'react';

function MoviePage() {
  const [currentPage, setCurrentPage] = useState(1);
  const { movies } = useAllMovies(currentPage - 1, 5);
  const handlePaginatino = () => {
    if (movies && currentPage * 5 >= movies?.totalElements) {
      setCurrentPage(1);
      return;
    }
    setCurrentPage(currentPage + 1);
  };
  return (
    <div className="h-screen">
      <MovieTable movies={movies?.content} />
      <Button onPress={handlePaginatino}>Next</Button>
    </div>
  );
}

export default MoviePage;
