import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { cinemaService } from "@/features/cinema/services/cinemaService";

// Define the cache keys
export const cinemaKeys = {
  all: ["cinema"] as const,
  hall: (id: string) => [...cinemaKeys.all, "hall", id] as const,
};

// 1 MINUTE
const STALE_TIME = 6000;
const CANT_RETRY_TIME = 1;

export const useCinema = (hallId: string) => {
  const queryClient = useQueryClient();

  const hallQuery = useQuery({
    queryKey: cinemaKeys.hall(hallId),
    queryFn: () => cinemaService.getHallById(hallId),
    staleTime: STALE_TIME,
    retry: CANT_RETRY_TIME,
  });

  const reserveSeatMutation = useMutation({
    mutationFn: cinemaService.reserveSeatById,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: cinemaKeys.hall(hallId) });
    },
    onError: () => {
      console.log("Error");
    },
  });

  const releaseSeatMutation = useMutation({
    mutationFn: cinemaService.releaseSeatById,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: cinemaKeys.hall(hallId) });
    },
    onError: () => {
      console.log("Error");
    },
  });

  return {
    //Data and states
    hall: hallQuery.data,
    isLoading: hallQuery.isLoading,
    isError: hallQuery.isError,

    //actions
    reserveSeat: releaseSeatMutation.mutate,
    releaseSeat: releaseSeatMutation.mutate,
    isModifying: reserveSeatMutation.isPending || releaseSeatMutation.isPending,
  };
};
