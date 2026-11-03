import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EquipoTorneoModel } from '../models/equipo-torneo.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class EquipoTorneoService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/equipo-torneo';

  getPorTorneo(idTorneo: number) {
    return this.cliente.get<EquipoTorneoModel[]>(this.urlbase + '/torneo/' + idTorneo);
  }

  getPorEquipo(idEquipo: number) {
    return this.cliente.get<EquipoTorneoModel[]>(this.urlbase + '/equipo/' + idEquipo);
  }

  postCrear(equipoTorneo: EquipoTorneoModel) {
    return this.cliente.post<EquipoTorneoModel>(this.urlbase, equipoTorneo);
  }

  deleteEliminar(idEquipo: number, idTorneo: number) {
    return this.cliente.delete(this.urlbase + '/equipo/' + idEquipo + '/torneo/' + idTorneo, {
      responseType: 'text',
    });
  }
}
