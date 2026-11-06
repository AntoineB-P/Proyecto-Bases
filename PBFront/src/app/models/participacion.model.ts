export interface ParticipacionModel {
  idParticipacion?: number;
  idJugador: number;
  nombreJugador?: string | null;
  idPartido: number;
  titular: boolean;
  minutosJugados: number;
}
