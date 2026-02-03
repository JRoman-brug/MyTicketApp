import { Button, Form, Input } from '@heroui/react';
import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';

import { useNavigate } from 'react-router-dom';
import { editMovieSchema, type EditMovieData } from '../schemas/movieSchema';
import type { MovieSummaryType } from '../types/movieType';
import { useUpdateMovie } from '../hooks/useUpdateMovie';

interface EditMovieFromProps {
  readonly initialData: MovieSummaryType;
}

function EditMovieFrom({ initialData }: EditMovieFromProps) {
  const { updateMovie } = useUpdateMovie();
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm({
    resolver: zodResolver(editMovieSchema),
    defaultValues: {
      name: initialData.name,
      duration: initialData.duration,
      posterUrl: initialData.posterUrl,
    },
  });

  const onSubmit = (data: EditMovieData) => {
    console.log('Datos válidos:', data);
    updateMovie({ id: initialData.id, ...data });
    navigate('/movies');
    console.log('Uploading data:');
  };

  return (
    <Form
      onSubmit={handleSubmit(onSubmit)}
      className="w-1/2 flex flex-col items-center"
    >
      <Input
        label="Name"
        {...register('name')}
        isInvalid={!!errors.name}
        errorMessage={errors.name?.message}
      />
      <Input
        label="Duration"
        {...register('duration', { valueAsNumber: true })}
        isInvalid={!!errors.duration}
        errorMessage={errors.duration?.message}
      />
      <Input
        label="Poster's Url"
        {...register('posterUrl')}
        isInvalid={!!errors.posterUrl}
        errorMessage={errors.posterUrl?.message}
      />
      <Button type="submit" isLoading={isSubmitting} color="primary">
        Save movie
      </Button>
    </Form>
  );
}

export default EditMovieFrom;
