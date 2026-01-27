import CreateMovieFrom from '@/features/movies/components/CreateMovieFrom';

function CreateMoviePage() {
  return (
    <main className="w-screen h-screen flex flex-col justify-center items-center gap-4">
      <h1 className="font-bold text-white text-4xl">Create a movie</h1>
      <CreateMovieFrom />;
    </main>
  );
}

export default CreateMoviePage;
