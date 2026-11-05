import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: false,
  selector: 'app-barralateral',
  templateUrl: './barralateral.html',
  styleUrls: ['./barralateral.css']
})
export class BarraLateral {
  opciones = [
    { nombre: 'Dashboard', icono: 'bi-grid-1x2-fill', ruta: '/dashboard', listo: true, soloAdmin: false },
    { nombre: 'Posiciones', icono: 'bi-trophy-fill', ruta: '/posiciones', listo: true, soloAdmin: false },
    { nombre: 'Equipos', icono: 'bi-shield-shaded', ruta: '/equipos', listo: true, soloAdmin: false },
    { nombre: 'Jugadores', icono: 'bi-person-fill', ruta: '/jugadores', listo: true, soloAdmin: false },
    { nombre: 'Partidos', icono: 'bi-calendar-event-fill', ruta: '/partidos', listo: true, soloAdmin: false },
    { nombre: 'Árbitros', icono: 'bi-flag-fill', ruta: '/arbitros', listo: true, soloAdmin: false },
    { nombre: 'Estadios', icono: 'bi-building', ruta: '/estadios', listo: true, soloAdmin: false },
    { nombre: 'Torneos', icono: 'bi-award-fill', ruta: '/torneos', listo: true, soloAdmin: false },
    { nombre: 'Panel admin', icono: 'bi-shield-lock-fill', ruta: '/admin', listo: true, soloAdmin: true }
  ];

  constructor(private authService: AuthService) {}

  get esAdmin(): boolean {
    return this.authService.esAdmin();
  }
}
