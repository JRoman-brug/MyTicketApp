import type { PaginatedResponse } from '@/common/types';
import { api } from '@/lib/axios';
import type { ShowtimeDetailsType } from '../types/showtimeType';

export const showtimeService = {
  getAllShowtime: async () => {
    const { data } =
      await api.get<PaginatedResponse<ShowtimeDetailsType>>(`/api/showtimes`);
    return data;
  },
};
