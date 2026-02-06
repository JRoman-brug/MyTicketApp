import { Button, Input } from '@heroui/react';
import { PlusIcon, Search } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

function ShowtimeTableHeader() {
  const navigate = useNavigate();
  const addHandler = () => {
    navigate(`create`);
  };
  return (
    <header className="w-full flex justify-between">
      <search>
        <Input placeholder="Search a movie" endContent={<Search />}></Input>
      </search>
      <Button color="primary" endContent={<PlusIcon />} onPress={addHandler}>
        Add
      </Button>
    </header>
  );
}

export default ShowtimeTableHeader;
