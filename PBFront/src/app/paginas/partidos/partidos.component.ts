import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { PartidoService } from '../../services/partido.service';
import { ResultadoService } from '../../services/resultado.service';
import { EquipoService } from '../../services/equipo.service';
import { EstadioService } from '../../services/estadio.service';
import { AuthService } from '../../services/auth.service';
import { PartidoModel } from '../../models/partido.model';
import { EquipoModel } from '../../models/equipo.model';
import { EstadioModel } from '../../models/estadio.model';

@Component({
  standalone: false,
  selector: 'partidos',
  templateUrl: './partidos.component.html',
  styleUrls: ['./partidos.component.css']
})
export class PartidosComponent implements OnInit {

  partidos: PartidoModel[] = [];
  equipos: EquipoModel[] = [];
  estadios: EstadioModel[] = [];
  idTorneo = 1;
  jornada = 1;

  formulario: PartidoModel = this.formularioVacio();
  partidoResultado: PartidoModel | null = null;
  golesLocal = 0;
  golesVisitante = 0;

  esAdmin = false;
  cargando = true;
  mostrarFormulario = false;
  editando = false;
  idEditando = 0;
  guardando = false;
  mensajeError = '';
  mensajeExito = '';

  constructor(
    private partidoService: PartidoService,
    private resultadoService: ResultadoService,
    private equipoService: EquipoService,
    private estadioService: EstadioService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.esAdmin = this.authService.esAdmin();
    this.cargar();
    this.equipoService.getListar().subscribe({
      next: (lista) => {
        this.equipos = lista;
        this.cdr.detectChanges();
      },
      error: () => {}
    });
    if (this.esAdmin) {
      this.estadioService.getListar().subscribe({
        next: (lista) => this.estadios = lista,
        error: () => {}
      });
    }
  }

  cargar(): void {
    this.cargando = true;
    this.partidoService.getListar({ idTorneo: this.idTorneo, jornada: this.jornada }).subscribe({
      next: (lista) => {
        this.partidos = lista;
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mensajeError = 'No se pudieron cargar los partidos. Revisa que el back esté prendido.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  anterior(): void {
    if (this.jornada > 1) {
      this.jornada--;
      this.partidoResultado = null;
      this.cargar();
    }
  }

  siguiente(): void {
    this.jornada++;
    this.partidoResultado = null;
    this.cargar();
  }

  escudoDe(idEquipo: number): string {
    for (let e of this.equipos) {
      if (e.idEquipo === idEquipo) {
        return e.escudoUrl || '';
      }
    }
    return '';
  }

  jugado(partido: PartidoModel): boolean {
    return partido.golesLocal != null && partido.golesVisitante != null;
  }

  formularioVacio(): PartidoModel {
    return { idTorneo: this.idTorneo, fecha: '', hora: '', jornada: this.jornada, idEstadio: 0, idLocal: 0, idVisitante: 0 };
  }

  nuevo(): void {
    this.formulario = this.formularioVacio();
    this.editando = false;
    this.mostrarFormulario = true;
    this.partidoResultado = null;
    this.mensajeError = '';
    this.mensajeExito = '';
  }

  editar(partido: PartidoModel): void {
    this.formulario = { ...partido };
    this.formulario.hora = partido.hora.substring(0, 5);
    this.idEditando = partido.idPartido || 0;
    this.editando = true;
    this.mostrarFormulario = true;
    this.partidoResultado = null;
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
      this.partidoService.putActualizar(this.idEditando, this.formulario).subscribe({
        next: () => this.guardado('Partido actualizado.'),
        error: (err) => this.falloGuardar(err)
      });
    } else {
      this.partidoService.postCrear(this.formulario).subscribe({
        next: () => this.guardado('Partido creado.'),
        error: (err) => this.falloGuardar(err)
      });
    }
  }

  guardado(mensaje: string): void {
    this.mensajeExito = mensaje;
    this.mostrarFormulario = false;
    this.guardando = false;
    this.jornada = this.formulario.jornada;
    this.cargar();
  }

  falloGuardar(err: any): void {
    this.mensajeError = this.leerError(err);
    this.guardando = false;
    this.cdr.detectChanges();
  }

  abrirResultado(partido: PartidoModel): void {
    this.partidoResultado = partido;
    this.golesLocal = partido.golesLocal || 0;
    this.golesVisitante = partido.golesVisitante || 0;
    this.mostrarFormulario = false;
    this.mensajeError = '';
    this.mensajeExito = '';
  }

  guardarResultado(): void {
    if (this.partidoResultado == null) {
      return;
    }
    const idPartido = this.partidoResultado.idPartido || 0;
    const resultado = { idPartido: idPartido, golesLocal: this.golesLocal, golesVisitante: this.golesVisitante };
    this.guardando = true;
    if (this.jugado(this.partidoResultado)) {
      this.resultadoService.putActualizar(idPartido, resultado).subscribe({
        next: () => this.resultadoGuardado(),
        error: (err) => this.falloGuardar(err)
      });
    } else {
      this.resultadoService.postCrear(resultado).subscribe({
        next: () => this.resultadoGuardado(),
        error: (err) => this.falloGuardar(err)
      });
    }
  }

  resultadoGuardado(): void {
    this.mensajeExito = 'Resultado guardado.';
    this.partidoResultado = null;
    this.guardando = false;
    this.cargar();
  }

  eliminar(partido: PartidoModel): void {
    if (!confirm('¿Seguro que quieres eliminar ' + partido.nombreLocal + ' vs ' + partido.nombreVisitante + '?')) {
      return;
    }
    this.partidoService.deleteEliminar(partido.idPartido || 0).subscribe({
      next: () => {
        this.mensajeExito = 'Partido eliminado.';
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
