import { Button, DatePicker, Form } from '@heroui/react';
import type {
  ShowtimeDetailsType,
  UpdateShowtimeType,
} from '../types/showtimeType';
import { Controller, useForm, type SubmitHandler } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import {
  updateShowtimeSchema,
  type updateShowtimeSchemaType,
} from '../schema/showtimeSchema';
import { parseAbsoluteToLocal } from '@internationalized/date';
import { useUpdateShowtime } from '../hooks/useUpdateShowtime';
import SelectMovieInput from './SelectMovieInput';
import { useEffect } from 'react';

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
  useEffect(() => {
    console.log(errors);
  }, [errors]);
  const onSubmit: SubmitHandler<UpdateShowtimeType> = (
    data: updateShowtimeSchemaType
  ) => {
    console.log(data);
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
      <SelectMovieInput control={control} initialMovie={showtime.movie} />
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
