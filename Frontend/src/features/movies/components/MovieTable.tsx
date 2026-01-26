import {
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
} from '@heroui/react';
import { useCallback, type Key } from 'react';
import type { MovieType } from '../types/movieType';
import MovieAction from './MovieActions';

const columns = [
  { name: 'Name', uid: 'NAME' },
  { name: 'Duration', uid: 'DURATION' },
  { name: 'Actions', uid: 'ACTIONS' },
];

interface MovieTableProps {
  readonly movies: MovieType[] | undefined;
}

function MovieTable({ movies }: MovieTableProps) {
  const renderCell = useCallback((movie: MovieType, columnKey: Key) => {
    switch (columnKey) {
      case 'NAME':
        return `${movie.name}`;
      case 'DURATION': // Si la agregas a columnas
        return `${movie.duration} min`;
      case 'ACTIONS':
        return <MovieAction movieId={movie.id} />;
      default:
        return 'error';
    }
  }, []);

  return (
    <Table aria-label="Table to manage movies">
      <TableHeader columns={columns}>
        {(column) => (
          <TableColumn
            key={column.uid}
            align={`${column.uid == 'ACTIONS' ? 'end' : 'start'}`}
          >
            {column.name}
          </TableColumn>
        )}
      </TableHeader>
      <TableBody emptyContent={'No movies found'} items={movies || []}>
        {(item) => (
          <TableRow key={item.id}>
            {(columnKey) => (
              <TableCell>{renderCell(item, columnKey)}</TableCell>
            )}
          </TableRow>
        )}
      </TableBody>
    </Table>
  );
}

export default MovieTable;
