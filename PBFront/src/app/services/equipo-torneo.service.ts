import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EquipoTorneoModel } from '../models/equipo-torneo.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class EquipoTorneoService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/equipo-torneo';

  postCrear(equipoTorneo: EquipoTorneoModel) {
    return this.cliente.post<EquipoTorneoModel>(this.urlbase, equipoTorneo);
  }

  deleteEliminar(idEquipo: number, idTorneo: number) {
    return this.cliente.delete(this.urlbase + '/equipo/' + idEquipo + '/torneo/' + idTorneo, {
      responseType: 'text',
    });
  }
}
