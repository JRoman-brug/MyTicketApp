import ShowtimeUpdateForm from '@/features/shotime/components/ShowtimeUpdateForm';
import { Button } from '@heroui/react';

function UpdateShowtimePage() {
  return (
    <main className="w-screen h-screen flex flex-col justify-center items-center gap-4 relative">
      <Button color="primary" className="absolute top-5 left-5">
        Back
      </Button>
      <h1 className="font-bold text-white text-4xl">Edit showtime</h1>
      <div className="w-1/2">
        <ShowtimeUpdateForm />
      </div>
    </main>
  );
}

export default UpdateShowtimePage;
