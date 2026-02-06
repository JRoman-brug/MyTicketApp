import z from 'zod';
const localDateTimeRegex = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}(?:\.\d+)?$/;
const baseShowtimeSchema = z.object({
  id: z.number(),
  startTime: z.string().regex(localDateTimeRegex),
  movieId: z.number(),
  hallId: z.number(),
});

export const createShowtimeSchema = baseShowtimeSchema.omit({
  id: true,
});
export type createShowtimeSchemaType = z.infer<typeof createShowtimeSchema>;

export const updateShowtimeSchema = baseShowtimeSchema
  .omit({
    movieId: true,
  })
  .partial({
    hallId: true,
    startTime: true,
  });

export type updateShowtimeSchemaType = z.infer<typeof updateShowtimeSchema>;
