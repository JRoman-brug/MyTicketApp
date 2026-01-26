import { Button, Input } from '@heroui/react';
import { PlusIcon, Search } from 'lucide-react';

function MovieTableHeader() {
  return (
    <header className="w-full flex justify-between">
      <search>
        <Input placeholder="Search a movie" endContent={<Search />}></Input>
      </search>
      <Button color="primary" endContent={<PlusIcon />}>
        Add
      </Button>
    </header>
  );
}

export default MovieTableHeader;
