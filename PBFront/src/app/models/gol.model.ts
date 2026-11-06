export interface GolModel {
  idGol?: number;
  idParticipacion: number;
  tipo: string;
  minuto: number;
}

export const TIPOS_GOL = ['Jugada', 'Penal', 'Tiro libre', 'Cabeza'];
