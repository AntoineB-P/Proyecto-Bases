import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { JugadorModel } from '../models/jugador.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class JugadorService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/jugadores';

  getListar() {
    return this.cliente.get<JugadorModel[]>(this.urlbase);
  }

  /** El back exige idEquipo e idTorneo juntos. */
  getPlantilla(idEquipo: number, idTorneo: number) {
    const params = new HttpParams()
      .set('idEquipo', idEquipo.toString())
      .set('idTorneo', idTorneo.toString());
    return this.cliente.get<JugadorModel[]>(this.urlbase, { params });
  }

  getPorPosicion(posicion: string) {
    const params = new HttpParams().set('posicion', posicion);
    return this.cliente.get<JugadorModel[]>(this.urlbase, { params });
  }

  getPorId(id: number) {
    return this.cliente.get<JugadorModel>(this.urlbase + '/' + id);
  }

  postCrear(jugador: JugadorModel) {
    return this.cliente.post<JugadorModel>(this.urlbase, jugador);
  }

  putActualizar(id: number, jugador: JugadorModel) {
    return this.cliente.put<JugadorModel>(this.urlbase + '/' + id, jugador);
  }

  deleteEliminar(id: number) {
    return this.cliente.delete(this.urlbase + '/' + id, { responseType: 'text' });
  }
}
