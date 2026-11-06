export interface InscripcionModel {
  idEquipo: number;
  nombreEquipo?: string | null;
  idTorneo: number;
  idJugador: number;
  nombreJugador?: string | null;
  dorsal: number;
  fechaInicio: string;
  fechaFin?: string | null;
}
