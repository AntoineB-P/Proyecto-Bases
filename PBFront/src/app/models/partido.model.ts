export interface PartidoModel {
  idPartido?: number;
  idTorneo: number;
  fecha: string;
  hora: string;
  jornada: number;
  idEstadio: number;
  nombreEstadio?: string | null;
  idLocal: number;
  nombreLocal?: string | null;
  idVisitante: number;
  nombreVisitante?: string | null;
  golesLocal?: number | null;
  golesVisitante?: number | null;
}
