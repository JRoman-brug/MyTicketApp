import { Button, DatePicker, Form } from '@heroui/react';

import { Controller, useForm, type SubmitHandler } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';

import { parseDateTime } from '@internationalized/date';
import type { ShowtimeDetailsType } from '@/features/shotime/types/showtimeType';
import { useUpdateShowtime } from '@/features/shotime/hooks/useUpdateShowtime';
import {
  updateShowtimeSchema,
  type updateShowtimeSchemaType,
} from '@/features/shotime/schema/showtimeSchema';
import SelectHallInput from '@/features/shotime/components/inputs/SelectHallInput';

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
      startTime: showtime.startTime,
      hallId: showtime.hall.id,
    },
  });
  const onSubmit: SubmitHandler<updateShowtimeSchemaType> = (
    data: updateShowtimeSchemaType
  ) => {
    console.log(data);
    updateShowtime({ ...data });
  };
  return (
    <Form
      onSubmit={handleSubmit(onSubmit)}
      aria-label="Form to update a showtime"
      className="flex flex-col gap-2 items-center"
      validationBehavior="aria"
    >
      <Controller
        name="startTime"
        control={control}
        render={({ field }) => (
          <DatePicker
            {...field}
            value={field.value ? parseDateTime(field.value) : null}
            onChange={(date) => {
              field.onChange(date ? date.toString() : null);
            }}
            isInvalid={!!errors.startTime}
            errorMessage={errors.startTime?.message}
            aria-label="Input to update the start time of showtime"
          />
        )}
      />
      <SelectHallInput control={control} name="hallId" label="Select a hall" />
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
