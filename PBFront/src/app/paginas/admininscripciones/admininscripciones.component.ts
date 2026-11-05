import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { InscripcionService } from '../../services/inscripcion.service';
import { EquipoService } from '../../services/equipo.service';
import { JugadorService } from '../../services/jugador.service';
import { InscripcionModel } from '../../models/inscripcion.model';
import { EquipoModel } from '../../models/equipo.model';
import { JugadorModel } from '../../models/jugador.model';

@Component({
  standalone: false,
  selector: 'admininscripciones',
  templateUrl: './admininscripciones.component.html'
})
export class AdminInscripcionesComponent implements OnInit {

  equipos: EquipoModel[] = [];
  jugadores: JugadorModel[] = [];
  inscripciones: InscripcionModel[] = [];
  idTorneo = 1;
  idEquipo = 0;
  formulario: InscripcionModel = this.formularioVacio();
  cargando = false;
  guardando = false;
  mensajeError = '';
  mensajeExito = '';

  constructor(
    private inscripcionService: InscripcionService,
    private equipoService: EquipoService,
    private jugadorService: JugadorService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.equipoService.getListar().subscribe({
      next: (lista) => {
        this.equipos = lista;
        this.cdr.detectChanges();
      },
      error: () => this.mensajeError = 'No se pudieron cargar los equipos.'
    });
    this.jugadorService.getListar().subscribe({
      next: (lista) => {
        this.jugadores = lista;
        this.cdr.detectChanges();
      },
      error: () => {}
    });
  }

  formularioVacio(): InscripcionModel {
    return { idEquipo: 0, idTorneo: this.idTorneo, idJugador: 0, dorsal: 1, fechaInicio: '' };
  }

  cargar(): void {
    if (this.idEquipo === 0) {
      return;
    }
    this.cargando = true;
    this.inscripcionService.getPorEquipoYTorneo(this.idEquipo, this.idTorneo).subscribe({
      next: (lista) => {
        lista.sort((a, b) => a.dorsal - b.dorsal);
        this.inscripciones = lista;
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mensajeError = 'No se pudo cargar la plantilla.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  inscribir(): void {
    this.formulario.idEquipo = this.idEquipo;
    this.formulario.idTorneo = this.idTorneo;
    this.guardando = true;
    this.mensajeError = '';
    this.inscripcionService.postCrear(this.formulario).subscribe({
      next: () => {
        this.mensajeExito = 'Jugador inscrito.';
        this.formulario = this.formularioVacio();
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

  quitar(inscripcion: InscripcionModel): void {
    if (!confirm('¿Quitar a ' + inscripcion.nombreJugador + ' de este equipo?')) {
      return;
    }
    this.inscripcionService.deleteEliminar(inscripcion.idEquipo, inscripcion.idTorneo, inscripcion.idJugador).subscribe({
      next: () => {
        this.mensajeExito = 'Inscripción eliminada.';
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
