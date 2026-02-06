import { DatePicker } from '@heroui/react';
import { parseDateTime } from '@internationalized/date';
import {
  Controller,
  type Control,
  type FieldValues,
  type Path,
} from 'react-hook-form';

interface DatePickerInputProps<T extends FieldValues> {
  readonly control: Control<T>;
  readonly name: Path<T>; // 'Path<T>' asegura que el string 'name' exista en tu Schema
  readonly label?: string;
}

export function DatePickerInput<T extends FieldValues>({
  control,
  name,
  label,
}: DatePickerInputProps<T>) {
  return (
    <Controller
      name={name}
      control={control}
      render={({ field }) => (
        <DatePicker
          name={field.name}
          onBlur={field.onBlur}
          ref={field.ref}
          label={label}
          value={field.value ? parseDateTime(field.value) : null}
          onChange={(date) => {
            field.onChange(date ? date.toString() : null);
          }}
          aria-label="Input to update the start time of showtime"
        />
      )}
    />
  );
}
