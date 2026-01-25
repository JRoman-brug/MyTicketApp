import { Outlet } from 'react-router-dom';

function RootLayout() {
  return (
    <main className="w-screen bg-blue-950">
      <Outlet />
    </main>
  );
}

export default RootLayout;
