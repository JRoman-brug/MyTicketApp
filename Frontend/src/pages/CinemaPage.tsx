import type { CinemaHall } from "@/features/cinema/types";
import { useParams } from "react-router-dom";

function CinemaPage() {
  const { hallId } = useParams<{ hallId: string }>() || "";
  const activeHallId = hallId || "sala-1";
  const hallInfo: CinemaHall = {
    id: activeHallId,
    name: "cine 1",
    cols: 2,
    rows: 12,
    seats: [],
  };

  return <div>Hall id: {hallInfo.name}</div>;
}

export default CinemaPage;
