import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EstadioModel } from '../models/estadio.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class EstadioService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/estadios';

  getListar() {
    return this.cliente.get<EstadioModel[]>(this.urlbase);
  }

  postCrear(estadio: EstadioModel) {
    return this.cliente.post<EstadioModel>(this.urlbase, estadio);
  }

  putActualizar(id: number, estadio: EstadioModel) {
    return this.cliente.put<EstadioModel>(this.urlbase + '/' + id, estadio);
  }

  deleteEliminar(id: number) {
    return this.cliente.delete(this.urlbase + '/' + id, { responseType: 'text' });
  }
}
