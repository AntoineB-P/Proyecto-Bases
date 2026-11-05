export interface EquipoModel {
  idEquipo?: number;
  nombre: string;
  fechaFundacion: string;
  ciudad: string;
  colores: string[];
  idSede: number;
  nombreEstadio?: string | null;
  idTecnico: number;
  nombreTecnico?: string | null;
  escudoUrl?: string | null;
}

export const COLORES_CAMISETA: any = {
  'Amarillo': '#FACC15',
  'Azul': '#2563EB',
  'Azul claro': '#60A5FA',
  'Azul oscuro': '#1E3A8A',
  'Blanco': '#FFFFFF',
  'Dorado': '#D4AF37',
  'Naranja': '#F97316',
  'Negro': '#111111',
  'Rojo': '#DC2626',
  'Rojo escarlata': '#B91C1C',
  'Verde': '#16A34A',
  'Vinotinto': '#7F1D1D'
};
