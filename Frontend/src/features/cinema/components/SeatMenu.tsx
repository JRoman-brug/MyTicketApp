import { Button } from '@heroui/react';
import { useSeat } from '@/features/cinema/hooks/useSeat';
import type { Seat } from '../types';

interface SeatMenuProps {
  readonly hallId: string;
  readonly seatSelected: Seat;
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
    <div className="w-2/6 bg-green-400">
      <p>{seatSelected.seatId}</p>
      <p>{seatSelected.label}</p>
      <p>{seatSelected.row}</p>
      <p>{seatSelected.column}</p>
      <p>{seatSelected.status}</p>

      {seatSelected.status == 'AVAILABLE' ? (
        <Button color="danger" onPress={reserveHandler}>
          Reserve
        </Button>
      ) : (
        <Button color="primary" onPress={releaseHandler}>
          Release
        </Button>
      )}

      <Button onPress={onClose}>close</Button>
    </div>
  );
}

export default SeatMenu;
