// Definimos los estados posibles de un asiento
export type SeatStatus = 'AVAILABLE' | 'BLOCKED' | 'SELECTED';

export interface Seat {
  seatId: string;
  row: number;
  column: number;
  label: number;
  status: SeatStatus;
}

export interface CinemaHall {
  id: string;
  name: string;
  totalRows: number;
  totalColumns: number;
  seats: Seat[];
}
