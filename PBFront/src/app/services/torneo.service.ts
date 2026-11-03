import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TorneoModel } from '../models/torneo.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class TorneoService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/torneos';

  getListar() {
    return this.cliente.get<TorneoModel[]>(this.urlbase);
  }

  getPorId(id: number) {
    return this.cliente.get<TorneoModel>(this.urlbase + '/' + id);
  }

  postCrear(torneo: TorneoModel) {
    return this.cliente.post<TorneoModel>(this.urlbase, torneo);
  }

  putActualizar(id: number, torneo: TorneoModel) {
    return this.cliente.put<TorneoModel>(this.urlbase + '/' + id, torneo);
  }

  deleteEliminar(id: number) {
    return this.cliente.delete(this.urlbase + '/' + id, { responseType: 'text' });
  }
}
