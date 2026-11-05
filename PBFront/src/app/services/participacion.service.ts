import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ParticipacionModel } from '../models/participacion.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class ParticipacionService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/participaciones';

  getPorPartido(idPartido: number) {
    return this.cliente.get<ParticipacionModel[]>(this.urlbase + '/partido/' + idPartido);
  }

  postCrear(participacion: ParticipacionModel) {
    return this.cliente.post<ParticipacionModel>(this.urlbase, participacion);
  }

  putActualizar(id: number, participacion: ParticipacionModel) {
    return this.cliente.put<ParticipacionModel>(this.urlbase + '/' + id, participacion);
  }

  deleteEliminar(id: number) {
    return this.cliente.delete(this.urlbase + '/' + id, { responseType: 'text' });
  }
}
