import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { InscripcionModel } from '../models/inscripcion.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class InscripcionService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/inscripciones';

  private ruta(idEquipo: number, idTorneo: number, idJugador: number) {
    return this.urlbase + '/equipo/' + idEquipo + '/torneo/' + idTorneo + '/jugador/' + idJugador;
  }

  getPorEquipoYTorneo(idEquipo: number, idTorneo: number) {
    return this.cliente.get<InscripcionModel[]>(
      this.urlbase + '/equipo/' + idEquipo + '/torneo/' + idTorneo,
    );
  }

  postCrear(inscripcion: InscripcionModel) {
    return this.cliente.post<InscripcionModel>(this.urlbase, inscripcion);
  }

  putActualizar(idEquipo: number, idTorneo: number, idJugador: number, inscripcion: InscripcionModel) {
    return this.cliente.put<InscripcionModel>(this.ruta(idEquipo, idTorneo, idJugador), inscripcion);
  }

  deleteEliminar(idEquipo: number, idTorneo: number, idJugador: number) {
    return this.cliente.delete(this.ruta(idEquipo, idTorneo, idJugador), { responseType: 'text' });
  }
}
