import { Component } from '@angular/core';

@Component({
  standalone: false,
  selector: 'app-barralateral',
  templateUrl: './barralateral.html',
  styleUrls: ['./barralateral.css']
})
export class BarraLateral {
  opciones = [
    { nombre: 'Dashboard', icono: 'bi-grid-1x2-fill', ruta: '/dashboard', listo: true },
    { nombre: 'Posiciones', icono: 'bi-trophy-fill', ruta: '/posiciones', listo: true },
    { nombre: 'Equipos', icono: 'bi-shield-shaded', ruta: '/equipos', listo: true },
    { nombre: 'Jugadores', icono: 'bi-person-fill', ruta: '/jugadores', listo: true },
    { nombre: 'Partidos', icono: 'bi-calendar-event-fill', ruta: '/partidos', listo: true },
    { nombre: 'Árbitros', icono: 'bi-flag-fill', ruta: '/arbitros', listo: true },
    { nombre: 'Estadios', icono: 'bi-building', ruta: '/estadios', listo: true },
    { nombre: 'Torneos', icono: 'bi-award-fill', ruta: '/torneos', listo: true }
  ];
}
