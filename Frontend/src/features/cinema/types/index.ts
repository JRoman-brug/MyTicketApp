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

export interface CreateRequest {
  name: string;
  totalRows: number;
  totalColumns: number;
  rowLabels: string[];
  columnLabels: string[];
  schema: string[][];
}

export interface HallDetailsType {
  id: number;
  name: string;
  totalRows: number;
  totalColumns: number;
  seats: SeatSummarType[];
}

export interface HallSummaryType {
  id: number;
  name: string;
}

export interface SeatDetailsType {
  id: number;
  row: number;
  column: number;
  label: string;
  isAvailable: boolean;
}

export interface SeatSummarType {
  id: number;
  label: string;
}
