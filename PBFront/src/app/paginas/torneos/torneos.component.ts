import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { TorneoService } from '../../services/torneo.service';
import { AuthService } from '../../services/auth.service';
import { TorneoModel } from '../../models/torneo.model';

@Component({
  standalone: false,
  selector: 'torneos',
  templateUrl: './torneos.component.html',
  styleUrls: ['./torneos.component.css']
})
export class TorneosComponent implements OnInit {

  torneos: TorneoModel[] = [];
  formulario: TorneoModel = this.formularioVacio();
  esAdmin = false;
  cargando = true;
  mostrarFormulario = false;
  editando = false;
  idEditando = 0;
  guardando = false;
  mensajeError = '';
  mensajeExito = '';

  constructor(
    private torneoService: TorneoService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.esAdmin = this.authService.esAdmin();
    this.cargar();
  }

  cargar(): void {
    this.torneoService.getListar().subscribe({
      next: (lista) => {
        this.torneos = lista;
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mensajeError = 'No se pudieron cargar los torneos. Revisa que el back esté prendido.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  formularioVacio(): TorneoModel {
    return { nombre: '', anio: new Date().getFullYear(), semestre: 1, fechaInicio: '', fechaFin: '' };
  }

  nuevo(): void {
    this.formulario = this.formularioVacio();
    this.editando = false;
    this.mostrarFormulario = true;
    this.mensajeError = '';
    this.mensajeExito = '';
  }

  editar(torneo: TorneoModel): void {
    this.formulario = { ...torneo };
    this.idEditando = torneo.idTorneo || 0;
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
    if (this.editando) {
      this.torneoService.putActualizar(this.idEditando, this.formulario).subscribe({
        next: () => this.guardado('Torneo actualizado.'),
        error: (err) => this.falloGuardar(err)
      });
    } else {
      this.torneoService.postCrear(this.formulario).subscribe({
        next: () => this.guardado('Torneo creado.'),
        error: (err) => this.falloGuardar(err)
      });
    }
  }

  guardado(mensaje: string): void {
    this.mensajeExito = mensaje;
    this.mostrarFormulario = false;
    this.guardando = false;
    this.cargar();
  }

  falloGuardar(err: any): void {
    this.mensajeError = this.authService.leerError(err);
    this.guardando = false;
    this.cdr.detectChanges();
  }

  eliminar(torneo: TorneoModel): void {
    if (!confirm('¿Seguro que quieres eliminar el torneo ' + torneo.nombre + '?')) {
      return;
    }
    this.torneoService.deleteEliminar(torneo.idTorneo || 0).subscribe({
      next: () => {
        this.mensajeExito = 'Torneo eliminado.';
        this.cargar();
      },
      error: (err) => {
        this.mensajeError = this.authService.leerError(err);
        this.cdr.detectChanges();
      }
    });
  }
}
