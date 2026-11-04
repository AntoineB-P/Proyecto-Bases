import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { JugadorService } from '../../services/jugador.service';
import { EquipoService } from '../../services/equipo.service';
import { AuthService } from '../../services/auth.service';
import { JugadorModel, POSICIONES_JUGADOR } from '../../models/jugador.model';
import { EquipoModel } from '../../models/equipo.model';

@Component({
  standalone: false,
  selector: 'jugadores',
  templateUrl: './jugadores.component.html',
  styleUrls: ['./jugadores.component.css']
})
export class JugadoresComponent implements OnInit {

  todos: JugadorModel[] = [];
  jugadores: JugadorModel[] = [];
  equipos: EquipoModel[] = [];
  posiciones = POSICIONES_JUGADOR;
  idTorneo = 1;

  filtroEquipo = 0;
  filtroPosicion = '';
  filtroNombre = '';

  formulario: JugadorModel = this.formularioVacio();
  esAdmin = false;
  cargando = true;
  mostrarFormulario = false;
  editando = false;
  idEditando = 0;
  guardando = false;
  mensajeError = '';
  mensajeExito = '';

  constructor(
    private jugadorService: JugadorService,
    private equipoService: EquipoService,
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
  }

  cargar(): void {
    this.cargando = true;
    if (this.filtroEquipo > 0) {
      this.jugadorService.getPlantilla(this.filtroEquipo, this.idTorneo).subscribe({
        next: (lista) => this.mostrar(lista),
        error: () => this.falloCargar()
      });
    } else {
      this.jugadorService.getListar().subscribe({
        next: (lista) => this.mostrar(lista),
        error: () => this.falloCargar()
      });
    }
  }

  mostrar(lista: JugadorModel[]): void {
    this.todos = lista;
    this.filtrar();
    this.cargando = false;
    this.cdr.detectChanges();
  }

  falloCargar(): void {
    this.mensajeError = 'No se pudieron cargar los jugadores. Revisa que el back esté prendido.';
    this.cargando = false;
    this.cdr.detectChanges();
  }

  filtrar(): void {
    let texto = this.filtroNombre.trim().toLowerCase();
    let lista = [];
    for (let j of this.todos) {
      let nombre = (j.nombres + ' ' + j.apellidos).toLowerCase();
      let pasaPosicion = this.filtroPosicion === '' || j.posicion === this.filtroPosicion;
      let pasaNombre = texto === '' || nombre.includes(texto);
      if (pasaPosicion && pasaNombre) {
        lista.push(j);
      }
    }
    this.jugadores = lista;
  }

  cambiarEquipo(): void {
    this.cargar();
  }

  formularioVacio(): JugadorModel {
    return {
      nombres: '',
      apellidos: '',
      nacionalidad: 'Colombia',
      fechaNacimiento: '',
      posicion: 'Defensa',
      tirosPuerta: null,
      pases: null,
      entradasOportunas: null,
      porteriaImbatida: null
    };
  }

  nuevo(): void {
    this.formulario = this.formularioVacio();
    this.editando = false;
    this.mostrarFormulario = true;
    this.mensajeError = '';
    this.mensajeExito = '';
  }

  editar(jugador: JugadorModel): void {
    this.formulario = { ...jugador };
    this.idEditando = jugador.idJugador || 0;
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
      this.jugadorService.putActualizar(this.idEditando, this.formulario).subscribe({
        next: () => this.guardado('Jugador actualizado.'),
        error: (err) => this.falloGuardar(err)
      });
    } else {
      this.jugadorService.postCrear(this.formulario).subscribe({
        next: () => this.guardado('Jugador creado.'),
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

  eliminar(jugador: JugadorModel): void {
    if (!confirm('¿Seguro que quieres eliminar a ' + jugador.nombres + ' ' + jugador.apellidos + '?')) {
      return;
    }
    this.jugadorService.deleteEliminar(jugador.idJugador || 0).subscribe({
      next: () => {
        this.mensajeExito = 'Jugador eliminado.';
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
