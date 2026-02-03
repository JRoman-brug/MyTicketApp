import { Button, DateInput, Select, SelectItem } from '@heroui/react';
import { Form } from 'react-router-dom';

function ShowtimeUpdateForm() {
  return (
    <Form className="flex flex-col gap-2">
      <DateInput />
      <Select>
        <SelectItem>1</SelectItem>
        <SelectItem>2</SelectItem>
        <SelectItem>3</SelectItem>
      </Select>
      <Button color="primary">Update</Button>
    </Form>
  );
}

export default ShowtimeUpdateForm;
