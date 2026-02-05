import z from 'zod';

const baseShowtimeSchema = z.object({
  id: z.number(),
  startTime: z.iso.datetime(),
  movieId: z.number(),
  hallId: z.number(),
});

export const createShowtimeSchema = baseShowtimeSchema.omit({
  id: true,
});
export type createShowtimeSchemaType = z.infer<typeof createShowtimeSchema>;

export const updateShowtimeSchema = baseShowtimeSchema
  .omit({
    hallId: true,
  })
  .partial({
    movieId: true,
    startTime: true,
  });

export type updateShowtimeSchemaType = z.infer<typeof updateShowtimeSchema>;
