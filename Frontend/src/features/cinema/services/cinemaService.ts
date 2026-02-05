import { api } from '@/lib/axios';
import type {
  CinemaHallType,
  HallDetailsType,
  SeatType,
} from '@/features/cinema/types';

// Services only talk with the provider (this case is axios)
// We define all routes that communicate with the backend
export const cinemaService = {
  getHallById: async (hallId: string) => {
    const { data } = await api.get<CinemaHallType>(`/hall/${hallId}`);
    return data;
  },

  getAllHalls: async () => {
    const { data } = await api.get<HallDetailsType[]>(`/hall`);
    return data;
  },
  reserveSeatById: async (seatId: string) => {
    const { data } = await api.post<SeatType>(`/seat/${seatId}/reserve`);
    return data;
  },
  releaseSeatById: async (seatId: string) => {
    const { data } = await api.post<SeatType>(`/seat/${seatId}/release`);
    return data;
  },
};
