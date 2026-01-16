/// <reference types="vite/client" />

interface ImportMetaEnv {
  // Aquí definimos el "contrato" de tus variables
  readonly VITE_API_URL: string;
  readonly VITE_APP_NAME: string;
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
