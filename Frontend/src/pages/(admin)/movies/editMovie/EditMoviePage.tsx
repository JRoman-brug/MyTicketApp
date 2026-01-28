import EditMovieFrom from '@/features/movies/components/EditMovieForm';
import { useMovie } from '@/features/movies/hooks/useMovie';
import { Button } from '@heroui/react';

import { useNavigate, useParams } from 'react-router-dom';

function EditMoviePage() {
  const { id } = useParams();
  const { movie } = useMovie(Number(id));
  const navigate = useNavigate();
  const onBack = () => {
    navigate('/movies');
  };
  if (!movie) return <div>Not found</div>;
  return (
    <main className="w-screen h-screen flex flex-col justify-center items-center gap-4 relative">
      <Button
        color="primary"
        onPress={onBack}
        className="absolute top-5 left-5"
      >
        Back
      </Button>
      <h1 className="font-bold text-white text-4xl">Edit movie</h1>
      <EditMovieFrom initialData={movie} />
    </main>
  );
}

export default EditMoviePage;
