export interface TarjetaModel {
  idTarjeta?: number;
  idParticipacion: number;
  idTipo: number;
  color?: string | null;
  motivo: string;
  minuto: number;
}
