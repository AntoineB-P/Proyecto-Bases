import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PartidoModel } from '../models/partido.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class PartidoService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/partidos';

  getListar(filtros?: { idTorneo?: number; jornada?: number; idEquipo?: number }) {
    let params = new HttpParams();
    if (filtros?.idTorneo != null) {
      params = params.set('idTorneo', filtros.idTorneo.toString());
    }
    if (filtros?.jornada != null) {
      params = params.set('jornada', filtros.jornada.toString());
    }
    if (filtros?.idEquipo != null) {
      params = params.set('idEquipo', filtros.idEquipo.toString());
    }
    return this.cliente.get<PartidoModel[]>(this.urlbase, { params });
  }

  postCrear(partido: PartidoModel) {
    return this.cliente.post<PartidoModel>(this.urlbase, partido);
  }

  putActualizar(id: number, partido: PartidoModel) {
    return this.cliente.put<PartidoModel>(this.urlbase + '/' + id, partido);
  }

  deleteEliminar(id: number) {
    return this.cliente.delete(this.urlbase + '/' + id, { responseType: 'text' });
  }
}
