export interface TorneoModel {
  idTorneo?: number;
  nombre: string;
  anio: number;
  semestre: number;
  fechaInicio: string;
  fechaFin: string;
  duracionDias?: number;
}
