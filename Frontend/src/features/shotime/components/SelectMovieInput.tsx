import { useInfiniteMovies } from '@/features/movies/hooks/useInfinityMovies';
import type { MovieSummaryType } from '@/features/movies/types/movieType';
import { Select, SelectItem } from '@heroui/react';
import { useInfiniteScroll } from '@heroui/use-infinite-scroll';
import { useMemo, useState } from 'react';
import { Controller, type Control } from 'react-hook-form';
import type { updateShowtimeSchemaType } from '../schema/showtimeSchema';

interface SelectMovieInputProps {
  readonly initialMovie: MovieSummaryType;
  readonly control: Control<updateShowtimeSchemaType>;
}

function SelectMovieInput({ initialMovie, control }: SelectMovieInputProps) {
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
    <Controller
      name="movieId"
      control={control}
      render={({ field }) => (
        <Select
          label="Select a movie"
          placeholder="Select a movie"
          items={items}
          isLoading={isLoading}
          scrollRef={scrollerRef}
          name={field.name}
          onBlur={field.onBlur}
          ref={field.ref}
          selectedKeys={
            field.value ? new Set([String(field.value)]) : new Set()
          }
          onSelectionChange={(keys) => {
            const selectedId = Number(Array.from(keys)[0]);
            field.onChange(selectedId);
          }}
          onOpenChange={setIsOpen}
          selectionMode="single"
        >
          {(item) => (
            <SelectItem key={item.id} textValue={item.name}>
              <span className="text-small">{item.name}</span>
            </SelectItem>
          )}
        </Select>
      )}
    />
  );
}

export default SelectMovieInput;
