import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { TorneoService } from '../../services/torneo.service';
import { EquipoService } from '../../services/equipo.service';
import { ConsultaService } from '../../services/consulta.service';
import { PartidoService } from '../../services/partido.service';
import { JugadorService } from '../../services/jugador.service';
import { EstadioService } from '../../services/estadio.service';
import { ArbitroService } from '../../services/arbitro.service';
import { COLORES_CAMISETA } from '../../models/equipo.model';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {

  temporada = '';

  indicadores = [
    { titulo: 'Equipos', valor: 0, icono: 'bi-shield-shaded' },
    { titulo: 'Jugadores', valor: 0, icono: 'bi-person-fill' },
    { titulo: 'Partidos jugados', valor: 0, icono: 'bi-calendar-check-fill' },
    { titulo: 'Goles', valor: 0, icono: 'bi-bullseye' },
    { titulo: 'Promedio de goles', valor: 0, icono: 'bi-graph-up-arrow' },
    { titulo: 'Tarjetas amarillas', valor: 0, icono: 'bi-square-fill' },
    { titulo: 'Tarjetas rojas', valor: 0, icono: 'bi-square-fill' },
    { titulo: 'Estadios', valor: 0, icono: 'bi-building' }
  ];

  jugadoresPorPosicion = [
    { posicion: 'Portero', cantidad: 0 },
    { posicion: 'Defensa', cantidad: 0 },
    { posicion: 'Mediocampista', cantidad: 0 },
    { posicion: 'Atacante', cantidad: 0 }
  ];

  tablaPosiciones: any[] = [];
  proximosPartidos: any[] = [];
  ultimosResultados: any[] = [];
  goleadores: any[] = [];
  disciplina: any[] = [];
  estadios: any[] = [];
  arbitros: any[] = [];
  coloresCamiseta = COLORES_CAMISETA;

  idTorneo = 1;
  sinConexion = false;
  cargando = true;
  pendientes = 4;
  copiasCinta = [1, 2];
  totalJugadores = 0;

  constructor(
    private torneoService: TorneoService,
    private equipoService: EquipoService,
    private consultaService: ConsultaService,
    private partidoService: PartidoService,
    private jugadorService: JugadorService,
    private estadioService: EstadioService,
    private arbitroService: ArbitroService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarTorneo();
    this.cargarPosiciones();
    this.cargarPartidosYEstadios();
    this.cargarEstadisticas();
    this.cargarJugadores();
    this.cargarArbitros();
  }

  cargarTorneo(): void {
    this.torneoService.getListar().subscribe({
      next: (torneos) => {
        for (let t of torneos) {
          if (t.idTorneo === this.idTorneo) {
            this.temporada = t.nombre;
          }
        }
        this.cdr.detectChanges();
      },
      error: () => {}
    });
  }

  cargarPosiciones(): void {
    this.equipoService.getListar().subscribe({
      next: (equipos) => {
        this.consultaService.getTablaPosiciones(this.idTorneo).subscribe({
          next: (posiciones) => {
            let tabla = [];
            for (let p of posiciones) {
              let color1 = '#FFFFFF';
              let escudo = '';
              for (let e of equipos) {
                if (e.idEquipo === p.idEquipo) {
                  escudo = e.escudoUrl || '';
                  if (e.colores.length > 0) {
                    color1 = this.coloresCamiseta[e.colores[0]] || '#FFFFFF';
                  }
                }
              }
              tabla.push({
                nombre: p.equipo,
                color1: color1,
                escudo: escudo,
                pj: p.partidosJugados,
                pg: p.ganados,
                pe: p.empatados,
                pp: p.perdidos,
                gf: p.golesFavor,
                gc: p.golesContra,
                dg: p.diferencia,
                pts: p.puntos
              });
            }
            this.tablaPosiciones = tabla;
            this.indicadores[0].valor = equipos.length;
            this.terminar();
          },
          error: () => this.fallo()
        });
      },
      error: () => this.fallo()
    });
  }

  cargarPartidosYEstadios(): void {
    this.partidoService.getListar({ idTorneo: this.idTorneo }).subscribe({
      next: (partidos) => {
        let jugados = [];
        let proximos = [];
        let totalGoles = 0;
        for (let p of partidos) {
          if (p.golesLocal != null && p.golesVisitante != null) {
            jugados.push(p);
            totalGoles += p.golesLocal + p.golesVisitante;
          } else {
            proximos.push(p);
          }
        }

        let resultados = [];
        for (let i = jugados.length - 1; i >= 0 && resultados.length < 4; i--) {
          resultados.push({
            local: jugados[i].nombreLocal || '',
            golesLocal: jugados[i].golesLocal || 0,
            golesVisitante: jugados[i].golesVisitante || 0,
            visitante: jugados[i].nombreVisitante || ''
          });
        }
        this.ultimosResultados = resultados;

        let siguientes = [];
        for (let i = 0; i < proximos.length && siguientes.length < 4; i++) {
          siguientes.push({
            local: proximos[i].nombreLocal || '',
            visitante: proximos[i].nombreVisitante || '',
            fecha: proximos[i].fecha,
            hora: proximos[i].hora.substring(0, 5),
            estadio: proximos[i].nombreEstadio || ''
          });
        }
        this.proximosPartidos = siguientes;

        this.indicadores[2].valor = jugados.length;
        this.indicadores[3].valor = totalGoles;
        this.indicadores[4].valor = jugados.length > 0 ? Math.round((totalGoles / jugados.length) * 10) / 10 : 0;

        this.estadioService.getListar().subscribe({
          next: (estadios) => {
            let lista = [];
            for (let e of estadios) {
              let cantidad = 0;
              for (let p of partidos) {
                if (p.idEstadio === e.idEstadio) {
                  cantidad++;
                }
              }
              lista.push({ nombre: e.nombre, ciudad: e.ciudad, capacidad: e.capacidad, partidos: cantidad });
            }
            lista.sort((a, b) => b.partidos - a.partidos);
            this.estadios = lista.slice(0, 4);
            this.indicadores[7].valor = estadios.length;
            this.terminar();
          },
          error: () => this.fallo()
        });
      },
      error: () => this.fallo()
    });
  }

  cargarEstadisticas(): void {
    this.consultaService.getEstadisticasJugadores(this.idTorneo).subscribe({
      next: (estadisticas) => {
        let amarillas = 0;
        let rojas = 0;
        let lista = [];
        for (let e of estadisticas) {
          amarillas += e.amarillas;
          rojas += e.rojas;
          lista.push({ nombre: e.nombreJugador, equipo: e.nombreEquipo || '', goles: e.goles, amarillas: e.amarillas, rojas: e.rojas });
        }

        this.goleadores = lista.slice(0, 5);

        let sancionados = lista.slice();
        sancionados.sort((a, b) => (b.amarillas + b.rojas * 2) - (a.amarillas + a.rojas * 2));
        this.disciplina = sancionados.slice(0, 5);

        this.indicadores[5].valor = amarillas;
        this.indicadores[6].valor = rojas;
        this.terminar();
      },
      error: () => this.fallo()
    });
  }

  cargarJugadores(): void {
    this.jugadorService.getListar().subscribe({
      next: (jugadores) => {
        for (let p of this.jugadoresPorPosicion) {
          p.cantidad = 0;
          for (let j of jugadores) {
            if (j.posicion === p.posicion) {
              p.cantidad++;
            }
          }
        }
        this.calcularTotalJugadores();
        this.indicadores[1].valor = jugadores.length;
        this.terminar();
      },
      error: () => this.fallo()
    });
  }

  cargarArbitros(): void {
    this.arbitroService.getListar().subscribe({
      next: (arbitros) => {
        let lista = [];
        for (let a of arbitros) {
          lista.push({ nombre: a.nombres + ' ' + a.apellidos, categoria: a.categoria, partidos: a.partidosDirigidos || 0 });
        }
        lista.sort((a, b) => b.partidos - a.partidos);
        this.arbitros = lista.slice(0, 4);
        this.cdr.detectChanges();
      },
      error: () => {}
    });
  }

  terminar(): void {
    this.pendientes--;
    if (this.pendientes === 0) {
      this.cargando = false;
    }
    this.cdr.detectChanges();
  }

  fallo(): void {
    this.sinConexion = true;
    this.terminar();
  }

  calcularTotalJugadores(): void {
    this.totalJugadores = 0;
    for (let p of this.jugadoresPorPosicion) {
      this.totalJugadores += p.cantidad;
    }
  }

  porNombre(_indice: number, item: { nombre: string }): string {
    return item.nombre;
  }

  porcentaje(cantidad: number): number {
    if (this.totalJugadores === 0) {
      return 0;
    }
    return Math.round((cantidad * 100) / this.totalJugadores);
  }
}
