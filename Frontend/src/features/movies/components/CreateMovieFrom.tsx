import { Button, Form, Input } from '@heroui/react';
import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import {
  createdMovieSchema,
  type CreatedMovieData,
} from '../schemas/movieSchema';
import { useCreateMovie } from '../hooks/useCreateMovie';
import { useNavigate } from 'react-router-dom';

function CreateMovieFrom() {
  const { createMovie } = useCreateMovie();
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm({
    resolver: zodResolver(createdMovieSchema),
  });

  const onSubmit = (data: CreatedMovieData) => {
    console.log('Datos válidos:', data);
    createMovie({ ...data });
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

export default CreateMovieFrom;
