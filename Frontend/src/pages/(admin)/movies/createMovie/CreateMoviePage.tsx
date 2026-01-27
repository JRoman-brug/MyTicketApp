import CreateMovieFrom from '@/features/movies/components/CreateMovieFrom';
import { Button } from '@heroui/react';
import { useNavigate } from 'react-router-dom';

function CreateMoviePage() {
  const navigate = useNavigate();
  const onBack = () => {
    navigate('/movies');
  };
  return (
    <main className="w-screen h-screen flex flex-col justify-center items-center gap-4 relative">
      <Button
        color="primary"
        onPress={onBack}
        className="absolute top-5 left-5"
      >
        Back
      </Button>
      <h1 className="font-bold text-white text-4xl">Create a movie</h1>
      <CreateMovieFrom />;
    </main>
  );
}

export default CreateMoviePage;
