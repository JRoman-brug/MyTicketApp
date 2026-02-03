export const showtimesKeys = {
  all: ['movies'] as const,
  lists: () => [...showtimesKeys.all, 'list'] as const, // Para el catálogo completo
  detail: (id: number) => [...showtimesKeys.all, 'detail', id] as const, // Para una peli sola
};

export const STALE_TIME = 6000;
export const CANT_RETRY_TIME = 1;
