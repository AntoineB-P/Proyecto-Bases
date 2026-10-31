import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {

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
    { nombre: 'Equipo A', color1: '#3B82F6', color2: '#FFFFFF', pj: 10, pg: 7, pe: 2, pp: 1, gf: 20, gc: 8, dg: 12, pts: 23 },
    { nombre: 'Equipo B', color1: '#EF4444', color2: '#FFFFFF', pj: 10, pg: 6, pe: 3, pp: 1, gf: 17, gc: 9, dg: 8, pts: 21 },
    { nombre: 'Equipo C', color1: '#22C55E', color2: '#FFFFFF', pj: 10, pg: 5, pe: 3, pp: 2, gf: 15, gc: 10, dg: 5, pts: 18 },
    { nombre: 'Equipo D', color1: '#FACC15', color2: '#0A0F1E', pj: 10, pg: 4, pe: 4, pp: 2, gf: 13, gc: 11, dg: 2, pts: 16 },
    { nombre: 'Equipo E', color1: '#8B5CF6', color2: '#FFFFFF', pj: 10, pg: 4, pe: 2, pp: 4, gf: 12, gc: 14, dg: -2, pts: 14 }
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

  ejemplo = false;
  cargando = false;
  copiasCinta = [1, 2];

  porNombre(_indice: number, item: { nombre: string }): string {
    return item.nombre;
  }

  totalJugadores = 0;

  constructor() {
    for (let p of this.jugadoresPorPosicion) {
      this.totalJugadores += p.cantidad;
    }
  }

  porcentaje(cantidad: number): number {
    return Math.round((cantidad * 100) / this.totalJugadores);
  }
}
