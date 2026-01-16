import CinemaPage from "@/pages/CinemaPage";
import { Route, Routes } from "react-router-dom";

function AppRoute() {
  return (
    <Routes>
      <Route path="/cinema/:hallId" Component={CinemaPage} />
    </Routes>
  );
}

export default AppRoute;
