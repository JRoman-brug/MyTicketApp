import {
  Controller,
  type Control,
  type FieldValues,
  type Path,
} from 'react-hook-form';
import { Select, SelectItem } from '@heroui/react';
import { useAllHall } from '@/features/cinema/hooks/useAllHall';

interface SelectHallInputProps<T extends FieldValues> {
  readonly control: Control<T>;
  readonly name: Path<T>; // 'Path<T>' asegura que el string 'name' exista en tu Schema
  readonly label?: string;
}

function SelectHallInput<T extends FieldValues>({
  control,
  name,
  label,
}: SelectHallInputProps<T>) {
  const { halls, isLoading } = useAllHall();
  return (
    <Controller
      name={name}
      control={control}
      render={({ field }) => (
        <Select
          label={label}
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
