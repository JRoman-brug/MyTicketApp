import type { Seat, SeatStatus } from '@/features/cinema/types';
import { Button } from '@heroui/react';
import { Armchair } from 'lucide-react';
import { useSeatStore } from '@/features/cinema/stores/seatStore';
interface SeatProps {
  readonly seat: Seat;
}

export function DummySeat() {
  return <div></div>;
}
export function Seat({ seat }: SeatProps) {
  const isSelected = useSeatStore((state) => state.selectedId === seat.seatId);
  const selectId = useSeatStore((state) => state.selectId);
  const getSeatColor = (status: SeatStatus, isSelected: boolean) => {
    if (isSelected) return 'bg-yellow-400 text-black';
    if (status === 'AVAILABLE') return 'bg-blue-300';
    if (status === 'RESERVE') return 'bg-red-500 cursor-not-allowed';

    return 'bg-blue-500 hover:bg-blue-600 text-white';
  };
  return (
    <Button
      onPress={() => selectId(seat.seatId)}
      aria-label={`select seat ${seat.label}`}
      className={`size-full ${getSeatColor(seat.status, isSelected)}  flex flex-col`}
    >
      <span className="font-bold">{seat.label}</span>
      <Armchair aria-hidden="true" size={32} />
    </Button>
  );
}
