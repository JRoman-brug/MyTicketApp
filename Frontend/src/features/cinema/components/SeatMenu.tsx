import { Button } from '@heroui/react';
import { useSeat } from '@/features/cinema/hooks/useSeat';
import type { SeatType } from '../types';

interface SeatMenuProps {
  readonly hallId: string;
  readonly seatSelected: SeatType;
  readonly onClose: () => void;
}

function SeatMenu({ seatSelected, hallId, onClose }: SeatMenuProps) {
  const cinema = useSeat(hallId);

  const reserveHandler = () => {
    if (!seatSelected) return;
    cinema.reserveSeat(seatSelected.seatId);
  };

  const releaseHandler = () => {
    if (!seatSelected) return;
    cinema.releaseSeat(seatSelected.seatId);
  };
  return (
    <div className="w-2/6 p-4 flex flex-col gap-2 rounded-sm bg-gray-400">
      <div>
        <p>Seat: {seatSelected.label}</p>
        <p>
          Status: <span>{seatSelected.status}</span>
        </p>
      </div>

      {seatSelected.status == 'AVAILABLE' ? (
        <Button className="w-full" color="danger" onPress={reserveHandler}>
          Reserve
        </Button>
      ) : (
        <Button className="w-full" color="primary" onPress={releaseHandler}>
          Release
        </Button>
      )}
      <Button onPress={onClose}>Close</Button>
    </div>
  );
}

export default SeatMenu;
