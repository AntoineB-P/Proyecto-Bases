import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EquipoModel } from '../models/equipo.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/usuario';

  postReenviarCodigo(correo: string) {
    return this.cliente.post(this.urlbase + '/reenviar-codigo', { correo }, { responseType: 'text' });
  }

  getFavorito() {
    return this.cliente.get<EquipoModel>(this.urlbase + '/favorito');
  }

  putMarcarFavorito(idEquipo: number) {
    return this.cliente.put(this.urlbase + '/favorito/' + idEquipo, null, { responseType: 'text' });
  }

  deleteQuitarFavorito() {
    return this.cliente.delete(this.urlbase + '/favorito', { responseType: 'text' });
  }
}
