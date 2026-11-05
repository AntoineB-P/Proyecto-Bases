export interface JugadorModel {
  idJugador?: number;
  nombres: string;
  apellidos: string;
  nacionalidad: string;
  fechaNacimiento: string;
  edad?: number;
  posicion: string;
  tirosPuerta?: number | null;
  pases?: number | null;
  entradasOportunas?: number | null;
  porteriaImbatida?: number | null;
}

export const POSICIONES_JUGADOR = ['Portero', 'Defensa', 'Mediocampista', 'Atacante'];
