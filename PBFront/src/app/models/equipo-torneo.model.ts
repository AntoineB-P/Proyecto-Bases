export interface EquipoTorneoModel {
  idEquipo: number;
  nombreEquipo?: string | null; // solo lectura
  idTorneo: number;
  nombreTorneo?: string | null; // solo lectura
}
