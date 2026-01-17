import { HeroUIProvider } from '@heroui/react';
import { QueryClientProvider } from '@tanstack/react-query';
import type { ReactNode } from 'react';
import { queryClient } from '@/lib/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';

interface ProvidersProps {
  readonly children: ReactNode;
}

function Providers({ children }: ProvidersProps) {
  return (
    <QueryClientProvider client={queryClient}>
      <HeroUIProvider>{children}</HeroUIProvider>
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  );
}

export default Providers;
