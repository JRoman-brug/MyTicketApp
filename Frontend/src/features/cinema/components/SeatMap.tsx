import { DummySeat, Seat } from '@/features/cinema/components/Seat';
import type { Seat as SeatType } from '@/features/cinema/types';
import { listToMatrix } from '../utils/seatUtils';
interface SeatMap {
  readonly rows: number;
  readonly columns: number;
  readonly seats: SeatType[];
  readonly selectSeat: (seat: SeatType) => void;
}
function SeatMap({ rows, columns, seats, selectSeat }: SeatMap) {
  const matrixSeat = listToMatrix(seats, rows, columns);
  return (
    <div
      className={`h-full grid bg-gray-300 gap-4 justify-items-center items-center`}
      style={{
        gridTemplateRows: `repeat(${rows}, minmax(0, 1fr))`,
        gridTemplateColumns: `repeat(${columns}, minmax(0, 1fr))`,
      }}
    >
      {matrixSeat.map((rows, rowIndex) =>
        rows.map((seat, colIndex) =>
          seat ? (
            <Seat key={seat.seatId} seat={seat} onClick={selectSeat} />
          ) : (
            <DummySeat key={`${rowIndex}-${colIndex}`} />
          )
        )
      )}
    </div>
  );
}

export default SeatMap;
