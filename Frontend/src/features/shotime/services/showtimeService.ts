import type { PaginatedResponse } from '@/common/types';
import { api } from '@/lib/axios';
import type {
  ShowtimeDetailsType,
  CreateShowtimeType,
  UpdateShowtimeType,
} from '../types/showtimeType';
import type { CalendarDate } from '@internationalized/date';

export const showtimeService = {
  getAllShowtime: async (page: number, size: number) => {
    const { data } = await api.get<PaginatedResponse<ShowtimeDetailsType>>(
      `/showtimes`,
      {
        params: {
          page,
          size,
        },
      }
    );
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

  updateShowtime: async (request: UpdateShowtimeType) => {
    const { data } = await api.patch(`/showtimes`, request);
    return data;
  },
  deleteShowtime: async (showtimeId: number) => {
    const { data } = await api.delete(`/showtimes/${showtimeId}`);
    return data;
  },

  getSeatStatus: async (showtimeId: number) => {
    const { data } = await api.get(`/showtimes/${showtimeId}/seatStatus`);
    return data;
  },

  getHallSchedule: async (hallId: number, day: CalendarDate) => {
    const dayFormatted = day.toString();
    const { data } = await api.get(`/showtimes/hallSchedule`, {
      params: {
        hallId,
        dayFormatted,
      },
    });
    return data;
  },
};
