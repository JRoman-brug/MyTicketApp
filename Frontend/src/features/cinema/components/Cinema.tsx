import type { CinemaHallType } from '../types';
import SeatMap from './SeatMap';

interface CinemaProps {
  readonly hall: CinemaHallType;
}
function Cinema({ hall }: CinemaProps) {
  return (
    <section
      key={'menu'}
      className="w-3/6 h-130 flex flex-col bg-gray-400 rounded-sm p-3"
    >
      <div className="text-black font-bold flex flex-col">
        <p>Hall name: {hall.name}</p>
      </div>
      <SeatMap
        rows={hall.totalRows}
        columns={hall.totalColumns}
        seats={hall.seats}
      />
    </section>
  );
}

export default Cinema;
