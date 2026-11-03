export interface PartidoModel {
  idPartido?: number;
  idTorneo: number;
  fecha: string;
  hora: string; // "19:30"
  jornada: number;
  idEstadio: number;
  nombreEstadio?: string | null; // solo lectura
  idLocal: number;
  nombreLocal?: string | null; // solo lectura
  idVisitante: number;
  nombreVisitante?: string | null; // solo lectura
  golesLocal?: number | null; // null si no se ha jugado
  golesVisitante?: number | null;
}
