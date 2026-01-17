import type { SeatType } from '../types';

export const listToMatrix = (
  seats: SeatType[],
  rows: number,
  columns: number
) => {
  const seatMatrix: (SeatType | null)[][] = Array.from({ length: rows }, () => {
    return new Array<SeatType | null>(columns).fill(null);
  });
  seats.forEach((seat) => {
    seatMatrix[seat.row][seat.column] = seat;
  });
  return seatMatrix;
};
