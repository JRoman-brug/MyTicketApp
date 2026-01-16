import { api } from "@/lib/axios";
import type { CinemaHall, Seat } from "@/features/cinema/types";

// Services only talk with the provider (this case is axios)
// We define all routes that communicate with the backend
export const cinemaService = {
  getHallById: async (hallId: string) => {
    const { data } = await api.get<CinemaHall>(`/hall/${hallId}`);
    return data;
  },
  reserveSeatById: async (seatId: string) => {
    const { data } = await api.post<Seat>(`/seat/${seatId}/reserve`);
    return data;
  },
  releaseSeatById: async (seatId: string) => {
    const { data } = await api.post<Seat>(`/seat/${seatId}/reserve`);
    return data;
  },
};
