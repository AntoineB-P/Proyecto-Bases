import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { ArbitroService } from '../../services/arbitro.service';
import { AuthService } from '../../services/auth.service';
import { ArbitroModel, CATEGORIAS_ARBITRO } from '../../models/arbitro.model';

@Component({
  standalone: false,
  selector: 'arbitros',
  templateUrl: './arbitros.component.html',
  styleUrls: ['./arbitros.component.css']
})
export class ArbitrosComponent implements OnInit {

  arbitros: ArbitroModel[] = [];
  categorias = CATEGORIAS_ARBITRO;
  formulario: ArbitroModel = this.formularioVacio();
  esAdmin = false;
  cargando = true;
  mostrarFormulario = false;
  editando = false;
  guardando = false;
  mensajeError = '';
  mensajeExito = '';
  totalFifa = 0;

  constructor(
    private arbitroService: ArbitroService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.esAdmin = this.authService.esAdmin();
    this.cargar();
  }

  cargar(): void {
    this.arbitroService.getListar().subscribe({
      next: (lista) => {
        lista.sort((a, b) => (b.partidosDirigidos || 0) - (a.partidosDirigidos || 0));
        this.arbitros = lista;
        this.totalFifa = 0;
        for (let a of lista) {
          if (a.categoria === 'FIFA') {
            this.totalFifa++;
          }
        }
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mensajeError = 'No se pudieron cargar los árbitros. Revisa que el back esté prendido.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  iniciales(arbitro: ArbitroModel): string {
    return (arbitro.nombres.substring(0, 1) + arbitro.apellidos.substring(0, 1)).toUpperCase();
  }

  formularioVacio(): ArbitroModel {
    return { cedula: '', nombres: '', apellidos: '', ciudad: '', categoria: 'Nacional' };
  }

  nuevo(): void {
    this.formulario = this.formularioVacio();
    this.editando = false;
    this.mostrarFormulario = true;
    this.mensajeError = '';
    this.mensajeExito = '';
  }

  editar(arbitro: ArbitroModel): void {
    this.formulario = { ...arbitro };
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
      ? this.arbitroService.putActualizar(this.formulario.cedula, this.formulario)
      : this.arbitroService.postCrear(this.formulario);

    peticion.subscribe({
      next: () => {
        this.mensajeExito = this.editando ? 'Árbitro actualizado.' : 'Árbitro creado.';
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

  eliminar(arbitro: ArbitroModel): void {
    if (!confirm('¿Seguro que quieres eliminar a ' + arbitro.nombres + ' ' + arbitro.apellidos + '?')) {
      return;
    }
    this.arbitroService.deleteEliminar(arbitro.cedula).subscribe({
      next: () => {
        this.mensajeExito = 'Árbitro eliminado.';
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
