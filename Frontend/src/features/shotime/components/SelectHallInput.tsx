import { Controller, type Control } from 'react-hook-form';
import type { updateShowtimeSchemaType } from '../schema/showtimeSchema';
import { Select, SelectItem } from '@heroui/react';
import { useAllHall } from '@/features/cinema/hooks/useAllHall';

interface SelectHallInputProps {
  readonly control: Control<updateShowtimeSchemaType>;
}

function SelectHallInput({ control }: SelectHallInputProps) {
  const { halls, isLoading } = useAllHall();
  return (
    <Controller
      name="hallId"
      control={control}
      render={({ field }) => (
        <Select
          label="Select a hall"
          placeholder="Select a hall"
          items={halls || []}
          isLoading={isLoading}
          name={field.name}
          onBlur={field.onBlur}
          ref={field.ref}
          selectedKeys={
            field.value ? new Set([String(field.value)]) : new Set()
          }
          onSelectionChange={(keys) => {
            const selectedStr = Array.from(keys)[0];
            const selectedNum = Number(selectedStr);
            console.log(selectedNum);
            field.onChange(selectedNum);
          }}
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

export default SelectHallInput;
