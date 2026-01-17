import Cinema from '@/features/cinema/components/Cinema';
import { useCinema } from '@/features/cinema/hooks/useCinema';
import { useParams } from 'react-router-dom';
import SeatMenu from '@/features/cinema/components/SeatMenu';
import { useSeatStore } from '@/features/cinema/stores/seatStore';

function CinemaPage() {
  const { hallId } = useParams<{ hallId: string }>() || '';
  const activeHallId = hallId || 'undefine';
  const { hall, isLoading } = useCinema(activeHallId);
  const selectedId = useSeatStore((state) => state.selectedId);

  const handleClose = () => useSeatStore.getState().selectId(null);

  if (isLoading) return <p>loading...</p>;
  if (hall == undefined) return <p>loading...</p>;
  const seatSelected = hall.seats.find((s) => s.seatId === selectedId);
  return (
    <div className="w-screen h-screen flex justify-center items-center bg-blue-950 gap-4">
      <Cinema hall={hall} />;
      {seatSelected && (
        <SeatMenu
          key={selectedId}
          seatSelected={seatSelected}
          hallId={activeHallId}
          onClose={handleClose}
        />
      )}
    </div>
  );
}

export default CinemaPage;
