import ShowtimeCreateForm from '@/features/shotime/components/Forms/ShowtimeCreateForm';

function CreateShowtimePage() {
  return (
    <main className="w-screen h-screen flex flex-col justify-center items-center gap-4 relative">
      <h1 className="text-white font-bold text-3xl">Create a showtime</h1>
      <ShowtimeCreateForm />
    </main>
  );
}

export default CreateShowtimePage;
