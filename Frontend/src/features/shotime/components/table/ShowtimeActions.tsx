import { Button } from '@heroui/react';
import { Pencil, Trash2 } from 'lucide-react';
import { useDeleteShowtime } from '@/features/shotime/hooks/useDeleteMovie';
import { useNavigate } from 'react-router-dom';
import ConfirmModal from '@/components/ConfirmModal';

interface ShowtimeActionsProps {
  readonly showtimeId: number;
}
function ShowtimeActions({ showtimeId }: ShowtimeActionsProps) {
  const navigate = useNavigate();
  const { deleteShowtime } = useDeleteShowtime();
  const editHandler = () => {
    navigate(`/showtimes/edit/${showtimeId}`);
  };
  const deleteHandler = () => {
    deleteShowtime(showtimeId);
    navigate('/showtimes');
  };
  return (
    <div className="flex gap-2 justify-end">
      <Button
        aria-label={`Edit showtime with id ${showtimeId}`}
        isIconOnly
        color="warning"
        onPress={editHandler}
      >
        <Pencil />
      </Button>
      <ConfirmModal
        title={`Delete showtime with id ${showtimeId}`}
        bodyText={`Are you sure delete this showtime?`}
        action={deleteHandler}
      >
        {(onOpen) => (
          <Button
            aria-label={`Delete showtime with id ${showtimeId}`}
            isIconOnly
            color="danger"
            onPress={onOpen}
          >
            <Trash2 />
          </Button>
        )}
      </ConfirmModal>
    </div>
  );
}

export default ShowtimeActions;
