import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TecnicoModel } from '../models/tecnico.model';
import { API_URL } from './api.config';

@Injectable({ providedIn: 'root' })
export class TecnicoService {
  private cliente = inject(HttpClient);
  private readonly urlbase = API_URL + '/tecnicos';

  getListar() {
    return this.cliente.get<TecnicoModel[]>(this.urlbase);
  }

  postCrear(tecnico: TecnicoModel) {
    return this.cliente.post<TecnicoModel>(this.urlbase, tecnico);
  }

  putActualizar(id: number, tecnico: TecnicoModel) {
    return this.cliente.put<TecnicoModel>(this.urlbase + '/' + id, tecnico);
  }

  deleteEliminar(id: number) {
    return this.cliente.delete(this.urlbase + '/' + id, { responseType: 'text' });
  }
}
