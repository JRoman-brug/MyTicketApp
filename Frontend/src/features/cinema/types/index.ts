// Definimos los estados posibles de un asiento
export type SeatStatus = "AVAILABLE" | "BLOCKED" | "SELECTED";

export interface Seat {
  id: string;
  row: string;
  number: number;
  status: SeatStatus;
}

export interface CinemaHall {
  id: string;
  name: string;
  rows: number;
  cols: number;
  seats: Seat[];
}
