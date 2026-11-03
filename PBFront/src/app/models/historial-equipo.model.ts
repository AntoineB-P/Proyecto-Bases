export interface HistorialEquipoModel {
  idPartido: number;
  fecha: string;
  jornada: number;
  rival: string;
  condicion: string; // 'LOCAL' | 'VISITANTE'
  golesFavor: number;
  golesContra: number;
  resultado: string; // 'G' | 'E' | 'P'
}
