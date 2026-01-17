import type { Seat } from '../types';

export const listToMatrix = (seats: Seat[], rows: number, columns: number) => {
  const seatMatrix: (Seat | null)[][] = Array.from({ length: rows }, () => {
    return new Array<Seat | null>(columns).fill(null);
  });
  seats.forEach((seat) => {
    seatMatrix[seat.row][seat.column] = seat;
  });
  return seatMatrix;
};
