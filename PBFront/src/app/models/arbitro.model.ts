export interface ArbitroModel {
  cedula: string;
  nombres: string;
  apellidos: string;
  ciudad: string;
  categoria: string;
  partidosDirigidos?: number;
}
export const CATEGORIAS_ARBITRO = ['Nacional', 'FIFA'];
