import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { TecnicoService } from '../../services/tecnico.service';
import { TecnicoModel } from '../../models/tecnico.model';

@Component({
  standalone: false,
  selector: 'admintecnicos',
  templateUrl: './admintecnicos.component.html'
})
export class AdminTecnicosComponent implements OnInit {

  tecnicos: TecnicoModel[] = [];
  formulario: TecnicoModel = this.formularioVacio();
  cargando = true;
  mostrarFormulario = false;
  editando = false;
  idEditando = 0;
  guardando = false;
  mensajeError = '';
  mensajeExito = '';

  constructor(private tecnicoService: TecnicoService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.tecnicoService.getListar().subscribe({
      next: (lista) => {
        this.tecnicos = lista;
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mensajeError = 'No se pudieron cargar los técnicos.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  formularioVacio(): TecnicoModel {
    return { nombres: '', apellidos: '', nacionalidad: 'Colombia', fechaNacimiento: '' };
  }

  nuevo(): void {
    this.formulario = this.formularioVacio();
    this.editando = false;
    this.mostrarFormulario = true;
    this.mensajeError = '';
    this.mensajeExito = '';
  }

  editar(tecnico: TecnicoModel): void {
    this.formulario = { ...tecnico };
    this.idEditando = tecnico.idTecnico || 0;
    this.editando = true;
    this.mostrarFormulario = true;
    this.mensajeError = '';
    this.mensajeExito = '';
  }

  guardar(): void {
    this.guardando = true;
    this.mensajeError = '';
    if (this.editando) {
      this.tecnicoService.putActualizar(this.idEditando, this.formulario).subscribe({
        next: () => this.guardado('Técnico actualizado.'),
        error: (err) => this.falloGuardar(err)
      });
    } else {
      this.tecnicoService.postCrear(this.formulario).subscribe({
        next: () => this.guardado('Técnico creado.'),
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
    this.mensajeError = this.leerError(err);
    this.guardando = false;
    this.cdr.detectChanges();
  }

  eliminar(tecnico: TecnicoModel): void {
    if (!confirm('¿Seguro que quieres eliminar a ' + tecnico.nombres + ' ' + tecnico.apellidos + '?')) {
      return;
    }
    this.tecnicoService.deleteEliminar(tecnico.idTecnico || 0).subscribe({
      next: () => {
        this.mensajeExito = 'Técnico eliminado.';
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
    if (err.error && err.error.error) {
      return err.error.error;
    }
    return 'No se pudo completar la acción.';
  }
}
