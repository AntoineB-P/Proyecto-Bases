export interface ArbitrajeModel {
  cedulaArbitro: string;
  nombreArbitro?: string | null;
  idPartido: number;
  rol: string;
}

export const ROLES_ARBITRAJE = ['Central', 'Asistente', 'Cuarto árbitro', 'VAR'];
