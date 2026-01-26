import { Button } from '@heroui/react';
import { Pencil, Trash2 } from 'lucide-react';
import { useDeleteMovie } from '../hooks/useDeleteMovie';

interface MovieActionProps {
  readonly movieId: number;
}
function MovieAction({ movieId }: MovieActionProps) {
  const { deleteMovie } = useDeleteMovie();
  const deleteHandler = () => {
    deleteMovie(movieId);
  };
  const editHandler = () => {
    console.log('Edit movie with id', movieId);
  };
  return (
    <>
      <Button
        isIconOnly
        aria-label={`Delete movie with ID: ${movieId}`}
        color="danger"
        className="mr-2"
        onPress={deleteHandler}
      >
        <Trash2 />
      </Button>
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
