import { Button, Form } from '@heroui/react';
import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import {
  createShowtimeSchema,
  type createShowtimeSchemaType,
} from '../../schema/showtimeSchema';
import { useEffect } from 'react';
import { DatePickerInput } from '@/features/shotime/components/inputs/DatePickerInput';
import SelectHallInput from '../inputs/SelectHallInput';
import SelectMovieInput from '../inputs/SelectMovieInput';
import { useCreateShowtime } from '../../hooks/useCreateShowtime';

function ShowtimeCreateForm() {
  const { createShowtime, error } = useCreateShowtime();
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: zodResolver(createShowtimeSchema),
  });

  useEffect(() => {
    console.log('Form error: ', errors);
    console.log('Backend ', error);
  }, [errors, error]);
  const onSubmit = (data: createShowtimeSchemaType) => {
    console.log(data);
    createShowtime({ ...data });
  };
  return (
    <Form
      onSubmit={handleSubmit(onSubmit)}
      className="w-1/2 flex flex-col items-center"
    >
      <DatePickerInput
        control={control}
        name="startTime"
        label="Date and time"
      />
      <SelectHallInput control={control} name="hallId" label="Select a hall" />
      <SelectMovieInput
        control={control}
        name="movieId"
        label="Select a movie"
      />
      <Button color="primary" type="submit">
        Create
      </Button>
    </Form>
  );
}

export default ShowtimeCreateForm;
