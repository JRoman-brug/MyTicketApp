const API_URL = import.meta.env.VITE_API_URL;
const APP_NAME = import.meta.env.VITE_APP_NAME;

if (!API_URL) {
  throw new Error("Missing essencial enviroment variables.");
}

export const config = {
  api: {
    baseUrl: API_URL || "http://localhost:8080/api",
  },
  app: {
    name: APP_NAME || "MyTicketApp", // Podemos poner un valor por defecto si falta
  },
} as const;
