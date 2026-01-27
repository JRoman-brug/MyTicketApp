import { z } from 'zod';
const baseMovieSchema = z.object({
  id: z.number(),
  name: z.string().min(1, 'The title must is requered').max(100),
  duration: z.number().gt(1, 'Duration must be greater that 1'),
  posterUrl: z.string(),
});

export const createdMovieSchema = baseMovieSchema.omit({
  id: true,
});

export type CreatedMovieData = z.infer<typeof createdMovieSchema>;
