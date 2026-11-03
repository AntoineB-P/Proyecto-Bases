import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PosicionModel } from '../models/posicion.model';
import { HistorialEquipoModel } from '../models/historial-equipo.model';
import { EstadisticaJugadorModel } from '../models/estadistica-jugador.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class ConsultaService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/consultas';

  getTablaPosiciones(idTorneo: number) {
    return this.cliente.get<PosicionModel[]>(this.urlbase + '/posiciones/torneo/' + idTorneo);
  }

  getHistorialEquipo(idEquipo: number, idTorneo: number) {
    return this.cliente.get<HistorialEquipoModel[]>(
      this.urlbase + '/historial/equipo/' + idEquipo + '/torneo/' + idTorneo,
    );
  }

  getEstadisticasJugadores(idTorneo: number) {
    return this.cliente.get<EstadisticaJugadorModel[]>(this.urlbase + '/estadisticas/torneo/' + idTorneo);
  }
}
