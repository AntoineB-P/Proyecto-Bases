import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { EstadioService } from '../../services/estadio.service';
import { AuthService } from '../../services/auth.service';
import { EstadioModel } from '../../models/estadio.model';

@Component({
  standalone: false,
  selector: 'estadios',
  templateUrl: './estadios.component.html',
  styleUrls: ['./estadios.component.css']
})
export class EstadiosComponent implements OnInit {

  estadios: EstadioModel[] = [];
  formulario: EstadioModel = this.formularioVacio();
  esAdmin = false;
  cargando = true;
  mostrarFormulario = false;
  editando = false;
  idEditando = 0;
  guardando = false;
  mensajeError = '';
  mensajeExito = '';
  capacidadTotal = 0;
  capacidadMayor = 1;

  constructor(
    private estadioService: EstadioService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.esAdmin = this.authService.esAdmin();
    this.cargar();
  }

  cargar(): void {
    this.estadioService.getListar().subscribe({
      next: (lista) => {
        lista.sort((a, b) => b.capacidad - a.capacidad);
        this.estadios = lista;
        this.capacidadTotal = 0;
        this.capacidadMayor = 1;
        for (let e of lista) {
          this.capacidadTotal += e.capacidad;
          if (e.capacidad > this.capacidadMayor) {
            this.capacidadMayor = e.capacidad;
          }
        }
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mensajeError = 'No se pudieron cargar los estadios. Revisa que el back esté prendido.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  porcentajeCapacidad(capacidad: number): number {
    return Math.round((capacidad * 100) / this.capacidadMayor);
  }

  formularioVacio(): EstadioModel {
    return { nombre: '', capacidad: 0, direccion: '', ciudad: '', barrio: '' };
  }

  nuevo(): void {
    this.formulario = this.formularioVacio();
    this.editando = false;
    this.mostrarFormulario = true;
    this.mensajeError = '';
    this.mensajeExito = '';
  }

  editar(estadio: EstadioModel): void {
    this.formulario = { ...estadio };
    this.idEditando = estadio.idEstadio || 0;
    this.editando = true;
    this.mostrarFormulario = true;
    this.mensajeError = '';
    this.mensajeExito = '';
    window.scrollTo(0, 0);
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.mensajeError = '';
  }

  guardar(): void {
    this.guardando = true;
    this.mensajeError = '';
    const peticion = this.editando
      ? this.estadioService.putActualizar(this.idEditando, this.formulario)
      : this.estadioService.postCrear(this.formulario);

    peticion.subscribe({
      next: () => {
        this.mensajeExito = this.editando ? 'Estadio actualizado.' : 'Estadio creado.';
        this.mostrarFormulario = false;
        this.guardando = false;
        this.cargar();
      },
      error: (err) => {
        this.mensajeError = this.leerError(err);
        this.guardando = false;
        this.cdr.detectChanges();
      }
    });
  }

  eliminar(estadio: EstadioModel): void {
    if (!confirm('¿Seguro que quieres eliminar el estadio ' + estadio.nombre + '?')) {
      return;
    }
    this.estadioService.deleteEliminar(estadio.idEstadio || 0).subscribe({
      next: () => {
        this.mensajeExito = 'Estadio eliminado.';
        this.cargar();
      },
      error: (err) => {
        this.mensajeError = this.leerError(err);
        this.cdr.detectChanges();
      }
    });
  }

  leerError(err: any): string {
    if (err.status === 401 || err.status === 403) {
      return 'Tu sesión expiró o no eres administrador. Inicia sesión de nuevo.';
    }
    let cuerpo = err.error;
    if (typeof cuerpo === 'string') {
      try {
        cuerpo = JSON.parse(cuerpo);
      } catch {
        return 'No se pudo completar la acción.';
      }
    }
    return cuerpo && cuerpo.error ? cuerpo.error : 'No se pudo completar la acción.';
  }
}
