import { useCallback, type Key } from 'react';
import type { ShowtimeDetailsType } from '../types/showtimeType';
import {
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
} from '@heroui/react';
import ShowtimeActions from './ShowtimeActions';

const columns = [
  { name: 'Id', uid: 'ID' },
  { name: 'Movie name', uid: 'MOVIE_NAME' },
  { name: 'Hall name', uid: 'HALL_NAME' },
  { name: 'Total seats', uid: 'TOTAL_SEATS' },
  { name: 'Available seat', uid: 'AVAILABLE_SEATS' },
  { name: 'Actions', uid: 'ACTIONS' },
];

interface ShowtimeTableProps {
  readonly showtimes: ShowtimeDetailsType[];
}

function ShowtimeTable({ showtimes }: ShowtimeTableProps) {
  const renderCell = useCallback(
    (showtime: ShowtimeDetailsType, columnKey: Key) => {
      switch (columnKey) {
        case 'ID':
          return showtime.id;
        case 'MOVIE_NAME':
          return showtime.movie.name;
        case 'HALL_NAME':
          return showtime.hall.name;
        case 'TOTAL_SEATS':
          return showtime.totalSeats;
        case 'AVAILABLE_SEATS':
          return showtime.availableSeats;
        case 'ACTIONS':
          return <ShowtimeActions showtimeId={showtime.id} />;
        default:
          return 'Unkwon';
      }
    },
    []
  );

  return (
    <Table>
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
      <TableBody emptyContent={'No movies found'} items={showtimes || []}>
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

export default ShowtimeTable;
