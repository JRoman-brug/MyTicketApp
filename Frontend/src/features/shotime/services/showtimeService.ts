import type { PaginatedResponse } from '@/common/types';
import { api } from '@/lib/axios';
import type {
  ShowtimeDetailsType,
  CreateShowtimeType,
} from '../types/showtimeType';

export const showtimeService = {
  getAllShowtime: async () => {
    const { data } =
      await api.get<PaginatedResponse<ShowtimeDetailsType>>(`/showtimes`);
    return data;
  },
  getShowtimeById: async (showtimeId: number) => {
    const { data } = await api.get<ShowtimeDetailsType>(
      `/showtimes/${showtimeId}`
    );
    return data;
  },

  createShowtime: async (request: CreateShowtimeType) => {
    const { data } = await api.post(`/showtimes`, request);
    return data;
  },
};
