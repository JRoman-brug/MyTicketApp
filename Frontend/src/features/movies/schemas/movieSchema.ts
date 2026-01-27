import { z } from 'zod';
const baseMovieSchema = z.object({
  id: z.number(),
  name: z.string(),
  duration: z.number(),
  posterUrl: z.string(),
});

export const createdMovieSchema = baseMovieSchema.omit({
  id: true,
});
