import ShowtimeTable from '@/features/shotime/components/ShowtimeTable';
import { useAllShowtime } from '@/features/shotime/hooks/useAllShowtimes';

function ShowtimePage() {
  const { showtimes } = useAllShowtime(0, 10);
  if (!showtimes) return <p>Nothing</p>;
  return (
    <main className="h-screen w-screen">
      <ShowtimeTable showtimes={showtimes.content} />;
    </main>
  );
}

export default ShowtimePage;
