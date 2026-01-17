import type { CinemaHall, Seat } from '../types';
import SeatMap from './SeatMap';

interface CinemaProps {
  readonly hall: CinemaHall;
  readonly selectSeat: (seat: Seat) => void;
}
function Cinema({ hall, selectSeat }: CinemaProps) {
  return (
    <section key={'menu'} className="w-3/6 h-130 flex flex-col bg-gray-500 p-2">
      <div>
        Hall id: {hall.id}
        Hall name: {hall.name}
      </div>
      <SeatMap
        rows={hall.totalRows}
        columns={hall.totalColumns}
        seats={hall.seats}
        selectSeat={selectSeat}
      />
    </section>
  );
}

export default Cinema;
