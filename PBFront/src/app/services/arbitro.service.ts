import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ArbitroModel } from '../models/arbitro.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class ArbitroService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/arbitros';

  getListar() {
    return this.cliente.get<ArbitroModel[]>(this.urlbase);
  }

  postCrear(arbitro: ArbitroModel) {
    return this.cliente.post<ArbitroModel>(this.urlbase, arbitro);
  }

  putActualizar(cedula: string, arbitro: ArbitroModel) {
    return this.cliente.put<ArbitroModel>(this.urlbase + '/' + cedula, arbitro);
  }

  deleteEliminar(cedula: string) {
    return this.cliente.delete(this.urlbase + '/' + cedula, { responseType: 'text' });
  }
}
