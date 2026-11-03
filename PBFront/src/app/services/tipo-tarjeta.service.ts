import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TipoTarjetaModel } from '../models/tipo-tarjeta.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class TipoTarjetaService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/tipos-tarjeta';

  getListar() {
    return this.cliente.get<TipoTarjetaModel[]>(this.urlbase);
  }

  getPorId(id: number) {
    return this.cliente.get<TipoTarjetaModel>(this.urlbase + '/' + id);
  }

  postCrear(tipo: TipoTarjetaModel) {
    return this.cliente.post<TipoTarjetaModel>(this.urlbase, tipo);
  }

  putActualizar(id: number, tipo: TipoTarjetaModel) {
    return this.cliente.put<TipoTarjetaModel>(this.urlbase + '/' + id, tipo);
  }

  deleteEliminar(id: number) {
    return this.cliente.delete(this.urlbase + '/' + id, { responseType: 'text' });
  }
}
