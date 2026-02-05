import { Button, DatePicker, Form, Select, SelectItem } from '@heroui/react';
import type {
  ShowtimeDetailsType,
  UpdateShowtimeType,
} from '../types/showtimeType';
import { useMemo, useState } from 'react';
import { useInfiniteMovies } from '@/features/movies/hooks/useInfinityMovies';
import { useInfiniteScroll } from '@heroui/use-infinite-scroll';
import type { MovieSummaryType } from '@/features/movies/types/movieType';
import { Controller, useForm, type SubmitHandler } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import {
  updateShowtimeSchema,
  type updateShowtimeSchemaType,
} from '../schema/showtimeSchema';
import { parseAbsoluteToLocal } from '@internationalized/date';
import { useUpdateShowtime } from '../hooks/useUpdateShowtime';

interface SelectMovieInputProps {
  readonly value: number; // Recibe el ID simple (zod espera number)
  readonly onChange: (value: number) => void; // Devuelve el ID simple
  readonly initialMovie?: MovieSummaryType; // Para el truco de la peli inicial
  readonly isInvalid?: boolean;
  readonly errorMessage?: string;
}

function SelectMovieInput({
  value,
  onChange,
  initialMovie,
  isInvalid,
  errorMessage,
}: SelectMovieInputProps) {
  const [isOpen, setIsOpen] = useState(false);
  const { movies, hasMore, isLoading, onLoadMore } = useInfiniteMovies();

  // 1. Fusión de listas inteligente + Eliminación de duplicados
  const items = useMemo(() => {
    const loadedMovies = movies?.options ?? [];

    // Si tenemos peli inicial, la ponemos al principio
    const rawList = initialMovie
      ? [initialMovie, ...loadedMovies]
      : loadedMovies;

    // Usamos un Map por ID para eliminar duplicados si el infinite scroll trae la misma
    const uniqueMap = new Map(rawList.map((m) => [m.id, m]));
    return Array.from(uniqueMap.values());
  }, [movies, initialMovie]);

  // 2. Hook del Scroll
  const [, scrollerRef] = useInfiniteScroll({
    hasMore,
    isEnabled: isOpen,
    shouldUseLoader: false,
    onLoadMore: () => {
      onLoadMore();
    },
  });

  return (
    <Select
      label="Select a movie"
      placeholder="Select a movie"
      // Conexión de Datos
      items={items}
      isLoading={isLoading}
      // Control del Scroll
      scrollRef={scrollerRef}
      onOpenChange={setIsOpen}
      // Traducción de Tipos (Number <-> Set<String>)
      selectedKeys={value ? new Set([String(value)]) : new Set()}
      onSelectionChange={(keys) => {
        const selectedId = Number(Array.from(keys)[0]);
        onChange(selectedId);
      }}
      // Errores
      isInvalid={isInvalid}
      errorMessage={errorMessage}
      selectionMode="single"
    >
      {(item) => (
        <SelectItem key={item.id} textValue={item.name}>
          <span className="text-small">{item.name}</span>
        </SelectItem>
      )}
    </Select>
  );
}

interface ShowtimeUpdateFormProps {
  readonly showtime: ShowtimeDetailsType;
}
function ShowtimeUpdateForm({ showtime }: ShowtimeUpdateFormProps) {
  const { updateShowtime } = useUpdateShowtime();
  const {
    control,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm({
    resolver: zodResolver(updateShowtimeSchema),
    defaultValues: {
      id: showtime.id,
      startTime: showtime.startTime + 'Z',
      movieId: showtime.movie.id,
    },
  });

  const onSubmit: SubmitHandler<UpdateShowtimeType> = (
    data: updateShowtimeSchemaType
  ) => {
    updateShowtime({ ...data });
  };
  return (
    <Form
      onSubmit={handleSubmit(onSubmit)}
      aria-label="Form to update a showtime"
      className="flex flex-col gap-2"
      validationBehavior="aria"
    >
      <Controller
        name="startTime"
        control={control}
        render={({ field }) => (
          <DatePicker
            {...field}
            value={field.value ? parseAbsoluteToLocal(field.value) : null}
            onChange={(value) => {
              console.log(`Value: ${value}, type: ${typeof value}`);

              field.onChange(value?.toAbsoluteString());
            }}
            isInvalid={!!errors.startTime}
            errorMessage={errors.startTime?.message}
            aria-label="Input to update the start time of showtime"
          />
        )}
      />
      <Controller
        name="movieId"
        control={control}
        render={({ field }) => (
          <SelectMovieInput
            // Pasamos control directo (Number)
            value={field.value || 0}
            onChange={field.onChange}
            // Pasamos la peli original para que aparezca seleccionada
            initialMovie={showtime.movie}
            isInvalid={!!errors.movieId}
            errorMessage={errors.movieId?.message}
          />
        )}
      />
      <Button
        aria-label="Button to submit the form"
        color="primary"
        type="submit"
        isLoading={isSubmitting}
      >
        Update
      </Button>
    </Form>
  );
}

export default ShowtimeUpdateForm;
