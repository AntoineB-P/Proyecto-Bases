import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {
  indicadores = [
    { titulo: 'Equipos', valor: 0, icono: 'bi-shield-shaded', color: 'azul' },
    { titulo: 'Jugadores', valor: 0, icono: 'bi-person-fill', color: 'verde' },
    { titulo: 'Partidos jugados', valor: 0, icono: 'bi-calendar-check-fill', color: 'amarillo' },
    { titulo: 'Goles', valor: 0, icono: 'bi-bullseye', color: 'rojo' }
  ];
}
