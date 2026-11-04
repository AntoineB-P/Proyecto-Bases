import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { TorneoService } from '../../services/torneo.service';
import { EquipoService } from '../../services/equipo.service';
import { ConsultaService } from '../../services/consulta.service';
import { PartidoService } from '../../services/partido.service';
import { JugadorService } from '../../services/jugador.service';
import { EstadioService } from '../../services/estadio.service';
import { ArbitroService } from '../../services/arbitro.service';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {

  temporada = 'Temporada de ejemplo';

  indicadores = [
    { titulo: 'Equipos', valor: 20, icono: 'bi-shield-shaded', color: 'azul' },
    { titulo: 'Jugadores', valor: 110, icono: 'bi-person-fill', color: 'verde' },
    { titulo: 'Partidos jugados', valor: 50, icono: 'bi-calendar-check-fill', color: 'amarillo' },
    { titulo: 'Goles', valor: 128, icono: 'bi-bullseye', color: 'rojo' },
    { titulo: 'Promedio de goles', valor: 2.5, icono: 'bi-graph-up-arrow', color: 'azul' },
    { titulo: 'Tarjetas amarillas', valor: 164, icono: 'bi-square-fill', color: 'amarillo' },
    { titulo: 'Tarjetas rojas', valor: 12, icono: 'bi-square-fill', color: 'rojo' },
    { titulo: 'Estadios', valor: 18, icono: 'bi-building', color: 'verde' }
  ];

  tablaPosiciones = [
    { nombre: 'Equipo A', color1: '#3B82F6', color2: '#FFFFFF', escudo: '', pj: 10, pg: 7, pe: 2, pp: 1, gf: 20, gc: 8, dg: 12, pts: 23 },
    { nombre: 'Equipo B', color1: '#EF4444', color2: '#FFFFFF', escudo: '', pj: 10, pg: 6, pe: 3, pp: 1, gf: 17, gc: 9, dg: 8, pts: 21 },
    { nombre: 'Equipo C', color1: '#22C55E', color2: '#FFFFFF', escudo: '', pj: 10, pg: 5, pe: 3, pp: 2, gf: 15, gc: 10, dg: 5, pts: 18 },
    { nombre: 'Equipo D', color1: '#FACC15', color2: '#0A0F1E', escudo: '', pj: 10, pg: 4, pe: 4, pp: 2, gf: 13, gc: 11, dg: 2, pts: 16 },
    { nombre: 'Equipo E', color1: '#8B5CF6', color2: '#FFFFFF', escudo: '', pj: 10, pg: 4, pe: 2, pp: 4, gf: 12, gc: 14, dg: -2, pts: 14 }
  ];

  proximosPartidos = [
    { local: 'Equipo A', visitante: 'Equipo C', fecha: 'Sáb 24 oct', hora: '4:00 p. m.', estadio: 'Estadio 1' },
    { local: 'Equipo B', visitante: 'Equipo E', fecha: 'Sáb 24 oct', hora: '6:10 p. m.', estadio: 'Estadio 2' },
    { local: 'Equipo D', visitante: 'Equipo A', fecha: 'Dom 25 oct', hora: '3:30 p. m.', estadio: 'Estadio 3' },
    { local: 'Equipo E', visitante: 'Equipo B', fecha: 'Dom 25 oct', hora: '5:45 p. m.', estadio: 'Estadio 4' }
  ];

  ultimosResultados = [
    { local: 'Equipo A', golesLocal: 2, golesVisitante: 1, visitante: 'Equipo D' },
    { local: 'Equipo C', golesLocal: 0, golesVisitante: 0, visitante: 'Equipo B' },
    { local: 'Equipo E', golesLocal: 1, golesVisitante: 3, visitante: 'Equipo A' },
    { local: 'Equipo D', golesLocal: 2, golesVisitante: 2, visitante: 'Equipo C' }
  ];

  goleadores = [
    { nombre: 'Jugador 1', equipo: 'Equipo A', goles: 9 },
    { nombre: 'Jugador 2', equipo: 'Equipo B', goles: 7 },
    { nombre: 'Jugador 3', equipo: 'Equipo A', goles: 6 },
    { nombre: 'Jugador 4', equipo: 'Equipo C', goles: 5 },
    { nombre: 'Jugador 5', equipo: 'Equipo E', goles: 5 }
  ];

  disciplina = [
    { nombre: 'Jugador 6', equipo: 'Equipo D', amarillas: 6, rojas: 1 },
    { nombre: 'Jugador 7', equipo: 'Equipo E', amarillas: 5, rojas: 0 },
    { nombre: 'Jugador 8', equipo: 'Equipo B', amarillas: 4, rojas: 1 },
    { nombre: 'Jugador 9', equipo: 'Equipo C', amarillas: 4, rojas: 0 },
    { nombre: 'Jugador 10', equipo: 'Equipo A', amarillas: 3, rojas: 0 }
  ];

  jugadoresPorPosicion = [
    { posicion: 'Portero', cantidad: 12, color: '#FACC15' },
    { posicion: 'Defensa', cantidad: 34, color: '#3B82F6' },
    { posicion: 'Mediocampista', cantidad: 38, color: '#22C55E' },
    { posicion: 'Atacante', cantidad: 26, color: '#EF4444' }
  ];

  estadios = [
    { nombre: 'Estadio 1', ciudad: 'Ciudad 1', capacidad: 48000, partidos: 6 },
    { nombre: 'Estadio 2', ciudad: 'Ciudad 2', capacidad: 36000, partidos: 5 },
    { nombre: 'Estadio 3', ciudad: 'Ciudad 3', capacidad: 28000, partidos: 5 },
    { nombre: 'Estadio 4', ciudad: 'Ciudad 4', capacidad: 20000, partidos: 4 }
  ];

  arbitros = [
    { nombre: 'Árbitro 1', categoria: 'FIFA', partidos: 8 },
    { nombre: 'Árbitro 2', categoria: 'Nacional', partidos: 7 },
    { nombre: 'Árbitro 3', categoria: 'FIFA', partidos: 6 },
    { nombre: 'Árbitro 4', categoria: 'Nacional', partidos: 6 }
  ];

  coloresCamiseta: any = {
    'Amarillo': '#FACC15',
    'Azul': '#2563EB',
    'Azul claro': '#60A5FA',
    'Azul oscuro': '#1E3A8A',
    'Blanco': '#FFFFFF',
    'Dorado': '#D4AF37',
    'Naranja': '#F97316',
    'Negro': '#111111',
    'Rojo': '#DC2626',
    'Rojo escarlata': '#B91C1C',
    'Verde': '#16A34A',
    'Vinotinto': '#7F1D1D'
  };

  idTorneo = 1;
  ejemplo = false;
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
  ) {
    this.calcularTotalJugadores();
  }

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
              let color2 = '#FFFFFF';
              let escudo = '';
              for (let e of equipos) {
                if (e.idEquipo === p.idEquipo) {
                  escudo = e.escudoUrl || '';
                  if (e.colores.length > 0) {
                    color1 = this.coloresCamiseta[e.colores[0]] || '#FFFFFF';
                    color2 = this.coloresCamiseta[e.colores[e.colores.length - 1]] || '#FFFFFF';
                  }
                }
              }
              tabla.push({
                nombre: p.equipo,
                color1: color1,
                color2: color2,
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
    this.ejemplo = true;
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
