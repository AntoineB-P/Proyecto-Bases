export interface TarjetaModel {
  idTarjeta?: number;
  idParticipacion: number;
  idTipo: number;
  color?: string | null; // solo lectura
  motivo: string;
  minuto: number;
}
