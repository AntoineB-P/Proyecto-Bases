import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { EquipoService } from '../../services/equipo.service';
import { EstadioService } from '../../services/estadio.service';
import { TecnicoService } from '../../services/tecnico.service';
import { InscripcionService } from '../../services/inscripcion.service';
import { JugadorService } from '../../services/jugador.service';
import { EquipoTorneoService } from '../../services/equipo-torneo.service';
import { AuthService } from '../../services/auth.service';
import { UsuarioService } from '../../services/usuario.service';
import { EquipoModel, COLORES_CAMISETA } from '../../models/equipo.model';
import { EstadioModel } from '../../models/estadio.model';
import { TecnicoModel } from '../../models/tecnico.model';

@Component({
  standalone: false,
  selector: 'equipos',
  templateUrl: './equipos.component.html',
  styleUrls: ['./equipos.component.css']
})
export class EquiposComponent implements OnInit {

  equipos: EquipoModel[] = [];
  estadios: EstadioModel[] = [];
  tecnicos: TecnicoModel[] = [];
  elegido: EquipoModel | null = null;
  plantilla: any[] = [];
  cargandoPlantilla = false;
  posiciones = ['Portero', 'Defensa', 'Mediocampista', 'Atacante'];

  formulario: EquipoModel = this.formularioVacio();
  coloresTexto = '';
  idTorneo = 1;
  esAdmin = false;
  logueado = false;
  idFavorito = 0;
  cargando = true;
  mostrarFormulario = false;
  editando = false;
  idEditando = 0;
  guardando = false;
  mensajeError = '';
  mensajeExito = '';

  coloresCamiseta = COLORES_CAMISETA;

  constructor(
    private equipoService: EquipoService,
    private estadioService: EstadioService,
    private tecnicoService: TecnicoService,
    private inscripcionService: InscripcionService,
    private jugadorService: JugadorService,
    private equipoTorneoService: EquipoTorneoService,
    private authService: AuthService,
    private usuarioService: UsuarioService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.esAdmin = this.authService.esAdmin();
    this.logueado = this.authService.estaLogueado();
    this.cargar();
    if (this.logueado) {
      this.usuarioService.getFavorito().subscribe({
        next: (equipo) => {
          this.idFavorito = equipo.idEquipo || 0;
          this.cdr.detectChanges();
        },
        error: () => this.idFavorito = 0
      });
    }
    if (this.esAdmin) {
      this.estadioService.getListar().subscribe({
        next: (lista) => this.estadios = lista,
        error: () => {}
      });
      this.tecnicoService.getListar().subscribe({
        next: (lista) => this.tecnicos = lista,
        error: () => {}
      });
    }
  }

  cargar(): void {
    this.equipoService.getListar().subscribe({
      next: (lista) => {
        this.equipos = lista;
        this.cargando = false;
        if (this.elegido == null && lista.length > 0) {
          this.verPlantilla(lista[0]);
        }
        this.cdr.detectChanges();
      },
      error: () => {
        this.mensajeError = 'No se pudieron cargar los equipos. Revisa que el back esté prendido.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  cambiarFavorito(equipo: EquipoModel): void {
    if (this.idFavorito === equipo.idEquipo) {
      this.usuarioService.deleteQuitarFavorito().subscribe({
        next: () => {
          this.idFavorito = 0;
          this.mensajeExito = equipo.nombre + ' ya no es tu equipo favorito.';
          this.cdr.detectChanges();
        },
        error: (err) => {
          this.mensajeError = this.authService.leerError(err);
          this.cdr.detectChanges();
        }
      });
    } else {
      this.usuarioService.putMarcarFavorito(equipo.idEquipo || 0).subscribe({
        next: () => {
          this.idFavorito = equipo.idEquipo || 0;
          this.mensajeExito = equipo.nombre + ' es ahora tu equipo favorito.';
          this.cdr.detectChanges();
        },
        error: (err) => {
          this.mensajeError = this.authService.leerError(err);
          this.cdr.detectChanges();
        }
      });
    }
  }

  colorDe(nombre: string): string {
    return this.coloresCamiseta[nombre] || '#FFFFFF';
  }

  verPlantilla(equipo: EquipoModel): void {
    this.elegido = equipo;
    this.plantilla = [];
    this.cargandoPlantilla = true;
    this.inscripcionService.getPorEquipoYTorneo(equipo.idEquipo || 0, this.idTorneo).subscribe({
      next: (inscripciones) => {
        this.jugadorService.getPlantilla(equipo.idEquipo || 0, this.idTorneo).subscribe({
          next: (jugadores) => {
            let lista = [];
            for (let i of inscripciones) {
              let posicion = '';
              for (let j of jugadores) {
                if (j.idJugador === i.idJugador) {
                  posicion = j.posicion;
                }
              }
              lista.push({ dorsal: i.dorsal, nombre: i.nombreJugador, posicion: posicion });
            }
            lista.sort((a, b) => a.dorsal - b.dorsal);
            this.plantilla = lista;
            this.cargandoPlantilla = false;
            this.cdr.detectChanges();
          },
          error: () => {
            this.cargandoPlantilla = false;
            this.cdr.detectChanges();
          }
        });
      },
      error: () => {
        this.cargandoPlantilla = false;
        this.cdr.detectChanges();
      }
    });
  }

  jugadoresDe(posicion: string): any[] {
    let lista = [];
    for (let j of this.plantilla) {
      if (j.posicion === posicion) {
        lista.push(j);
      }
    }
    return lista;
  }

  formularioVacio(): EquipoModel {
    return { nombre: '', fechaFundacion: '', ciudad: '', colores: [], idSede: 0, idTecnico: 0, escudoUrl: '' };
  }

  nuevo(): void {
    this.formulario = this.formularioVacio();
    this.coloresTexto = '';
    this.editando = false;
    this.mostrarFormulario = true;
    this.mensajeError = '';
    this.mensajeExito = '';
  }

  editar(equipo: EquipoModel): void {
    this.formulario = { ...equipo };
    this.coloresTexto = equipo.colores.join(', ');
    this.idEditando = equipo.idEquipo || 0;
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
    let colores = [];
    for (let c of this.coloresTexto.split(',')) {
      if (c.trim() !== '') {
        colores.push(c.trim());
      }
    }
    this.formulario.colores = colores;
    this.guardando = true;
    this.mensajeError = '';

    if (this.editando) {
      this.equipoService.putActualizar(this.idEditando, this.formulario).subscribe({
        next: () => this.guardado('Equipo actualizado.'),
        error: (err) => this.falloGuardar(err)
      });
    } else {
      this.equipoService.postCrear(this.formulario).subscribe({
        next: (creado) => {
          this.equipoTorneoService.postCrear({ idEquipo: creado.idEquipo || 0, idTorneo: this.idTorneo }).subscribe({
            next: () => this.guardado('Equipo creado e inscrito en el torneo.'),
            error: () => this.guardado('Equipo creado, pero no se pudo inscribir en el torneo.')
          });
        },
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

  eliminar(equipo: EquipoModel): void {
    if (!confirm('¿Seguro que quieres eliminar a ' + equipo.nombre + '?')) {
      return;
    }
    this.equipoService.deleteEliminar(equipo.idEquipo || 0).subscribe({
      next: () => {
        this.mensajeExito = 'Equipo eliminado.';
        this.elegido = null;
        this.cargar();
      },
      error: (err) => {
        this.mensajeError = this.authService.leerError(err);
        this.cdr.detectChanges();
      }
    });
  }
}
