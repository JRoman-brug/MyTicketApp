export const movieKeys = {
  all: ['movies'] as const,
  lists: () => [...movieKeys.all, 'list'] as const, // Para el catálogo completo
  detail: (id: number) => [...movieKeys.all, 'detail', id] as const, // Para una peli sola
};

export const STALE_TIME = 6000;
export const CANT_RETRY_TIME = 1;
