import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GolModel } from '../models/gol.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class GolService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/goles';

  getPorParticipacion(idParticipacion: number) {
    return this.cliente.get<GolModel[]>(this.urlbase + '/participacion/' + idParticipacion);
  }

  getPorPartido(idPartido: number) {
    return this.cliente.get<GolModel[]>(this.urlbase + '/partido/' + idPartido);
  }

  getBuscar(idParticipacion: number, idGol: number) {
    return this.cliente.get<GolModel>(
      this.urlbase + '/participacion/' + idParticipacion + '/gol/' + idGol,
    );
  }

  postCrear(gol: GolModel) {
    return this.cliente.post<GolModel>(this.urlbase, gol);
  }

  putActualizar(idParticipacion: number, idGol: number, gol: GolModel) {
    return this.cliente.put<GolModel>(
      this.urlbase + '/participacion/' + idParticipacion + '/gol/' + idGol,
      gol,
    );
  }

  deleteEliminar(idParticipacion: number, idGol: number) {
    return this.cliente.delete(this.urlbase + '/participacion/' + idParticipacion + '/gol/' + idGol, {
      responseType: 'text',
    });
  }
}
