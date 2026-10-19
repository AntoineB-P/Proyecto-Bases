import { Component } from '@angular/core';

@Component({
  selector: 'app-barra-lateral',
  standalone: false,
  templateUrl: './barra-lateral.html',
  styleUrl: './barra-lateral.css'
})
export class BarraLateral {
  opciones = [
    { nombre: 'Dashboard', icono: 'bi-grid-1x2-fill', ruta: '/dashboard', listo: true },
    { nombre: 'Posiciones', icono: 'bi-trophy-fill', ruta: '/posiciones', listo: false },
    { nombre: 'Equipos', icono: 'bi-shield-shaded', ruta: '/equipos', listo: false },
    { nombre: 'Jugadores', icono: 'bi-person-fill', ruta: '/jugadores', listo: false },
    { nombre: 'Partidos', icono: 'bi-calendar-event-fill', ruta: '/partidos', listo: false },
    { nombre: 'Árbitros', icono: 'bi-flag-fill', ruta: '/arbitros', listo: false },
    { nombre: 'Estadios', icono: 'bi-building', ruta: '/estadios', listo: false },
    { nombre: 'Torneos', icono: 'bi-award-fill', ruta: '/torneos', listo: false }
  ];
}
