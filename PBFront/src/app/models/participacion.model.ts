export interface ParticipacionModel {
  idParticipacion?: number;
  idJugador: number;
  nombreJugador?: string | null; // solo lectura
  idPartido: number;
  titular: boolean;
  minutosJugados: number;
}
