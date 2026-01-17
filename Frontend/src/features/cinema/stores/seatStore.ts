// src/features/cinema/stores/seatStore.ts
import { create } from 'zustand';

interface SeatState {
  selectedId: string | null;
  selectId: (id: string | null) => void;
}

export const useSeatStore = create<SeatState>((set) => ({
  selectedId: null,
  selectId: (id) => set({ selectedId: id }),
}));
