import type { Seat } from '@/features/cinema/types';
import { Button } from '@heroui/react';

interface SeatProps {
  readonly seat: Seat;
  readonly onClick: (seat: Seat) => void;
}

export function DummySeat() {
  return <div></div>;
}
export function Seat({ seat, onClick }: SeatProps) {
  return (
    <div
      className={`w-full h-full ${seat.status == 'AVAILABLE' ? 'bg-green-500' : 'bg-red-700'}`}
    >
      {seat.label}
      <Button
        color="primary"
        onPress={() => {
          onClick(seat);
        }}
      >
        select
      </Button>
    </div>
  );
}
