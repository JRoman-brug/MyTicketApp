import Cinema from '@/features/cinema/components/Cinema';
import { useCinema } from '@/features/cinema/hooks/useCinema';
import { useState } from 'react';
import { useParams } from 'react-router-dom';
import SeatMenu from '@/features/cinema/components/SeatMenu';

function CinemaPage() {
  const { hallId } = useParams<{ hallId: string }>() || '';
  const activeHallId = hallId || 'undefine';
  const { hall, isLoading } = useCinema(activeHallId);
  const [selectedId, setSelectedId] = useState<string | null>(null);

  if (isLoading) return <p>loading...</p>;
  if (hall == undefined) return <p>loading...</p>;
  const seatSelected = hall.seats.find((s) => s.seatId === selectedId);
  return (
    <div className="w-screen h-screen flex justify-center items-center bg-blue-950 gap-4">
      <Cinema hall={hall} selectSeat={(seat) => setSelectedId(seat.seatId)} />;
      {seatSelected && (
        <SeatMenu
          key={selectedId}
          seatSelected={seatSelected}
          hallId={activeHallId}
          onClose={() => setSelectedId(null)}
        />
      )}
    </div>
  );
}

export default CinemaPage;
