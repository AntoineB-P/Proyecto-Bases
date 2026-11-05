import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ArbitrajeModel } from '../models/arbitraje.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class ArbitrajeService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/arbitrajes';

  getPorPartido(idPartido: number) {
    return this.cliente.get<ArbitrajeModel[]>(this.urlbase + '/partido/' + idPartido);
  }

  postCrear(arbitraje: ArbitrajeModel) {
    return this.cliente.post<ArbitrajeModel>(this.urlbase, arbitraje);
  }

  putActualizar(idPartido: number, cedula: string, arbitraje: ArbitrajeModel) {
    return this.cliente.put<ArbitrajeModel>(
      this.urlbase + '/partido/' + idPartido + '/arbitro/' + cedula,
      arbitraje,
    );
  }

  deleteEliminar(idPartido: number, cedula: string) {
    return this.cliente.delete(this.urlbase + '/partido/' + idPartido + '/arbitro/' + cedula, {
      responseType: 'text',
    });
  }
}
