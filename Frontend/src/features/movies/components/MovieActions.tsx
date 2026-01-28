import { Button } from '@heroui/react';
import { Pencil, Trash2 } from 'lucide-react';
import { useDeleteMovie } from '../hooks/useDeleteMovie';
import ConfirmModal from '@/components/ConfirmModal';
import { useNavigate } from 'react-router-dom';

interface MovieActionProps {
  readonly movieId: number;
}
function MovieAction({ movieId }: MovieActionProps) {
  const navigate = useNavigate();
  const { deleteMovie } = useDeleteMovie();
  const deleteHandler = () => {
    deleteMovie(movieId);
  };
  const editHandler = () => {
    navigate(`edit/${movieId}`);
  };
  return (
    <>
      <ConfirmModal
        title="Delete movie"
        bodyText={'Are you sure to delete this movie'}
        action={deleteHandler}
      >
        {(onOpen) => (
          <Button
            isIconOnly
            aria-label={`Delete movie with ID: ${movieId}`}
            color="danger"
            className="mr-2"
            onPress={onOpen}
          >
            <Trash2 />
          </Button>
        )}
      </ConfirmModal>
      <Button
        isIconOnly
        aria-label={`Edit movie with ID: ${movieId}`}
        color="warning"
        onPress={editHandler}
      >
        <Pencil />
      </Button>
    </>
  );
}

export default MovieAction;
