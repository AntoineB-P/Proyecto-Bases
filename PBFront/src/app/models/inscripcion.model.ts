export interface InscripcionModel {
  idEquipo: number;
  nombreEquipo?: string | null; // solo lectura
  idTorneo: number;
  idJugador: number;
  nombreJugador?: string | null; // solo lectura
  dorsal: number;
  fechaInicio: string;
  fechaFin?: string | null;
}
