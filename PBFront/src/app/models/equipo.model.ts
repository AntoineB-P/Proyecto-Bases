export interface EquipoModel {
  idEquipo?: number;
  nombre: string;
  fechaFundacion: string;
  ciudad: string;
  colores: string[];
  idSede: number;
  nombreEstadio?: string | null; // solo lectura
  idTecnico: number;
  nombreTecnico?: string | null; // solo lectura
}
