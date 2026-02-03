import { Button } from '@heroui/react';
import { Pencil, Trash2 } from 'lucide-react';

interface ShowtimeActionsProps {
  readonly showtimeId: number;
}
function ShowtimeActions({ showtimeId }: ShowtimeActionsProps) {
  return (
    <div className="flex gap-2 justify-end">
      <Button
        aria-label={`Delete showtime with id ${showtimeId}`}
        isIconOnly
        color="danger"
      >
        <Trash2 />
      </Button>
      <Button
        aria-label={`Edit showtime with id ${showtimeId}`}
        isIconOnly
        color="warning"
      >
        <Pencil />
      </Button>
    </div>
  );
}

export default ShowtimeActions;
