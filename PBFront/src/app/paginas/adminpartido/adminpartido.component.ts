import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { PartidoService } from '../../services/partido.service';
import { ArbitroService } from '../../services/arbitro.service';
import { ArbitrajeService } from '../../services/arbitraje.service';
import { ParticipacionService } from '../../services/participacion.service';
import { GolService } from '../../services/gol.service';
import { TarjetaService } from '../../services/tarjeta.service';
import { JugadorService } from '../../services/jugador.service';
import { PartidoModel } from '../../models/partido.model';
import { ArbitroModel } from '../../models/arbitro.model';
import { ArbitrajeModel, ROLES_ARBITRAJE } from '../../models/arbitraje.model';
import { ParticipacionModel } from '../../models/participacion.model';
import { GolModel, TIPOS_GOL } from '../../models/gol.model';
import { TarjetaModel } from '../../models/tarjeta.model';
import { JugadorModel } from '../../models/jugador.model';

@Component({
  standalone: false,
  selector: 'adminpartido',
  templateUrl: './adminpartido.component.html',
  styleUrls: ['./adminpartido.component.css']
})
export class AdminPartidoComponent implements OnInit {

  idTorneo = 1;
  jornada = 1;
  partidos: PartidoModel[] = [];
  partido: PartidoModel | null = null;
  idPartido = 0;

  arbitros: ArbitroModel[] = [];
  jugadores: JugadorModel[] = [];
  arbitrajes: ArbitrajeModel[] = [];
  participaciones: ParticipacionModel[] = [];
  goles: GolModel[] = [];
  tarjetas: TarjetaModel[] = [];

  rolesArbitraje = ROLES_ARBITRAJE;
  tiposGol = TIPOS_GOL;

  nuevoArbitraje: ArbitrajeModel = { cedulaArbitro: '', idPartido: 0, rol: 'Central' };
  nuevaParticipacion: ParticipacionModel = { idJugador: 0, idPartido: 0, titular: true, minutosJugados: 90 };
  nuevoGol: GolModel = { idParticipacion: 0, tipo: 'Jugada', minuto: 1 };
  nuevaTarjeta: TarjetaModel = { idParticipacion: 0, idTipo: 1, motivo: 'Amonestación', minuto: 1 };

  mensajeError = '';
  mensajeExito = '';

  constructor(
    private partidoService: PartidoService,
    private arbitroService: ArbitroService,
    private arbitrajeService: ArbitrajeService,
    private participacionService: ParticipacionService,
    private golService: GolService,
    private tarjetaService: TarjetaService,
    private jugadorService: JugadorService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarPartidos();
    this.arbitroService.getListar().subscribe({
      next: (lista) => this.arbitros = lista,
      error: () => {}
    });
  }

  cargarPartidos(): void {
    this.partido = null;
    this.partidoService.getListar({ idTorneo: this.idTorneo, jornada: this.jornada }).subscribe({
      next: (lista) => {
        this.partidos = lista;
        this.cdr.detectChanges();
      },
      error: () => {
        this.mensajeError = 'No se pudieron cargar los partidos.';
        this.cdr.detectChanges();
      }
    });
  }

  elegirPartido(): void {
    if (this.partido == null) {
      return;
    }
    this.idPartido = this.partido.idPartido || 0;
    this.jugadores = [];
    this.jugadorService.getPlantilla(this.partido.idLocal, this.idTorneo).subscribe({
      next: (lista) => this.agregarJugadores(lista),
      error: () => {}
    });
    this.jugadorService.getPlantilla(this.partido.idVisitante, this.idTorneo).subscribe({
      next: (lista) => this.agregarJugadores(lista),
      error: () => {}
    });
    this.cargarDetalle();
  }

  agregarJugadores(lista: JugadorModel[]): void {
    for (let j of lista) {
      this.jugadores.push(j);
    }
    this.cdr.detectChanges();
  }

  cargarDetalle(): void {
    const idPartido = this.idPartido;
    this.arbitrajeService.getPorPartido(idPartido).subscribe({
      next: (lista) => {
        this.arbitrajes = lista;
        this.cdr.detectChanges();
      },
      error: () => {}
    });
    this.participacionService.getPorPartido(idPartido).subscribe({
      next: (lista) => {
        this.participaciones = lista;
        this.cdr.detectChanges();
      },
      error: () => {}
    });
    this.golService.getPorPartido(idPartido).subscribe({
      next: (lista) => {
        this.goles = lista;
        this.cdr.detectChanges();
      },
      error: () => {}
    });
    this.tarjetaService.getPorPartido(idPartido).subscribe({
      next: (lista) => {
        this.tarjetas = lista;
        this.cdr.detectChanges();
      },
      error: () => {}
    });
  }

  nombreDe(idParticipacion: number): string {
    for (let p of this.participaciones) {
      if (p.idParticipacion === idParticipacion) {
        return p.nombreJugador || '';
      }
    }
    return '';
  }

  agregarArbitraje(): void {
    this.nuevoArbitraje.idPartido = this.idPartido;
    this.arbitrajeService.postCrear(this.nuevoArbitraje).subscribe({
      next: () => this.listo('Árbitro asignado.'),
      error: (err) => this.fallo(err)
    });
  }

  quitarArbitraje(a: ArbitrajeModel): void {
    this.arbitrajeService.deleteEliminar(a.idPartido, a.cedulaArbitro).subscribe({
      next: () => this.listo('Árbitro quitado.'),
      error: (err) => this.fallo(err)
    });
  }

  agregarParticipacion(): void {
    this.nuevaParticipacion.idPartido = this.idPartido;
    this.participacionService.postCrear(this.nuevaParticipacion).subscribe({
      next: () => this.listo('Jugador agregado a la alineación.'),
      error: (err) => this.fallo(err)
    });
  }

  quitarParticipacion(p: ParticipacionModel): void {
    this.participacionService.deleteEliminar(p.idParticipacion || 0).subscribe({
      next: () => this.listo('Jugador quitado de la alineación.'),
      error: (err) => this.fallo(err)
    });
  }

  agregarGol(): void {
    this.golService.postCrear(this.nuevoGol).subscribe({
      next: () => this.listo('Gol registrado.'),
      error: (err) => this.fallo(err)
    });
  }

  quitarGol(g: GolModel): void {
    this.golService.deleteEliminar(g.idParticipacion, g.idGol || 0).subscribe({
      next: () => this.listo('Gol eliminado.'),
      error: (err) => this.fallo(err)
    });
  }

  agregarTarjeta(): void {
    this.tarjetaService.postCrear(this.nuevaTarjeta).subscribe({
      next: () => this.listo('Tarjeta registrada.'),
      error: (err) => this.fallo(err)
    });
  }

  quitarTarjeta(t: TarjetaModel): void {
    this.tarjetaService.deleteEliminar(t.idParticipacion, t.idTarjeta || 0).subscribe({
      next: () => this.listo('Tarjeta eliminada.'),
      error: (err) => this.fallo(err)
    });
  }

  listo(mensaje: string): void {
    this.mensajeExito = mensaje;
    this.mensajeError = '';
    this.cargarDetalle();
  }

  fallo(err: any): void {
    this.mensajeError = this.authService.leerError(err);
    this.mensajeExito = '';
    this.cdr.detectChanges();
  }
}
