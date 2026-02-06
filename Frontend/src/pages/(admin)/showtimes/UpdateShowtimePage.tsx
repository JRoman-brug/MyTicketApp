import ShowtimeUpdateForm from '@/features/shotime/components/Forms/ShowtimeUpdateForm';
import { useShowtime } from '@/features/shotime/hooks/useShowtime';
import { Button } from '@heroui/react';
import { useNavigate, useParams } from 'react-router-dom';

function UpdateShowtimePage() {
  const { id } = useParams();
  const { showtime } = useShowtime(Number(id));
  const navigate = useNavigate();
  const handleBack = () => {
    navigate(`/showtimes`);
  };
  if (!showtime) return <p>not found</p>;
  return (
    <main className="w-screen h-screen flex flex-col justify-center items-center gap-4 relative">
      <Button
        color="primary"
        className="absolute top-5 left-5"
        onPress={handleBack}
      >
        Back
      </Button>
      <h1 className="font-bold text-white text-4xl">Edit showtime</h1>
      <div className="w-1/2">
        <ShowtimeUpdateForm showtime={showtime} />
      </div>
    </main>
  );
}

export default UpdateShowtimePage;
