import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ResultadoModel } from '../models/resultado.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class ResultadoService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/resultados';

  getPorPartido(idPartido: number) {
    return this.cliente.get<ResultadoModel>(this.urlbase + '/partido/' + idPartido);
  }

  getPorTorneo(idTorneo: number) {
    return this.cliente.get<ResultadoModel[]>(this.urlbase + '/torneo/' + idTorneo);
  }

  postCrear(resultado: ResultadoModel) {
    return this.cliente.post<ResultadoModel>(this.urlbase, resultado);
  }

  putActualizar(idPartido: number, resultado: ResultadoModel) {
    return this.cliente.put<ResultadoModel>(this.urlbase + '/partido/' + idPartido, resultado);
  }

  deleteEliminar(idPartido: number) {
    return this.cliente.delete(this.urlbase + '/partido/' + idPartido, { responseType: 'text' });
  }
}
