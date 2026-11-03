import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { EquipoModel } from '../models/equipo.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class EquipoService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/equipos';

  /** Con idTorneo trae solo los equipos de ese torneo. */
  getListar(idTorneo?: number) {
    let params = new HttpParams();
    if (idTorneo != null) {
      params = params.set('idTorneo', idTorneo.toString());
    }
    return this.cliente.get<EquipoModel[]>(this.urlbase, { params });
  }

  getPorId(id: number) {
    return this.cliente.get<EquipoModel>(this.urlbase + '/' + id);
  }

  postCrear(equipo: EquipoModel) {
    return this.cliente.post<EquipoModel>(this.urlbase, equipo);
  }

  putActualizar(id: number, equipo: EquipoModel) {
    return this.cliente.put<EquipoModel>(this.urlbase + '/' + id, equipo);
  }

  deleteEliminar(id: number) {
    return this.cliente.delete(this.urlbase + '/' + id, { responseType: 'text' });
  }
}
