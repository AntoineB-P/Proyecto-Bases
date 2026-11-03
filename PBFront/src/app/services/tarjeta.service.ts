import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TarjetaModel } from '../models/tarjeta.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class TarjetaService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/tarjetas';

  private ruta(idParticipacion: number, idTarjeta: number) {
    return this.urlbase + '/participacion/' + idParticipacion + '/tarjeta/' + idTarjeta;
  }

  getPorParticipacion(idParticipacion: number) {
    return this.cliente.get<TarjetaModel[]>(this.urlbase + '/participacion/' + idParticipacion);
  }

  getPorPartido(idPartido: number) {
    return this.cliente.get<TarjetaModel[]>(this.urlbase + '/partido/' + idPartido);
  }

  getBuscar(idParticipacion: number, idTarjeta: number) {
    return this.cliente.get<TarjetaModel>(this.ruta(idParticipacion, idTarjeta));
  }

  postCrear(tarjeta: TarjetaModel) {
    return this.cliente.post<TarjetaModel>(this.urlbase, tarjeta);
  }

  putActualizar(idParticipacion: number, idTarjeta: number, tarjeta: TarjetaModel) {
    return this.cliente.put<TarjetaModel>(this.ruta(idParticipacion, idTarjeta), tarjeta);
  }

  deleteEliminar(idParticipacion: number, idTarjeta: number) {
    return this.cliente.delete(this.ruta(idParticipacion, idTarjeta), { responseType: 'text' });
  }
}
