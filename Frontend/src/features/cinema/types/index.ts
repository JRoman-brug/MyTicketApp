// Definimos los estados posibles de un asiento
export type SeatStatus = 'AVAILABLE' | 'RESERVE';

export interface SeatType {
  seatId: string;
  row: number;
  column: number;
  label: number;
  status: SeatStatus;
}

export interface CinemaHallType {
  id: string;
  name: string;
  totalRows: number;
  totalColumns: number;
  seats: SeatType[];
}
