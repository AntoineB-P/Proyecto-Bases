import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: false,
  selector: 'admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  seccion = 'tecnicos';
  secciones = [
    { id: 'tecnicos', nombre: 'Técnicos', icono: 'bi-person-badge' },
    { id: 'inscripciones', nombre: 'Inscripciones', icono: 'bi-card-checklist' },
    { id: 'partido', nombre: 'Detalle de partido', icono: 'bi-clipboard-data' },
    { id: 'usuarios', nombre: 'Usuarios', icono: 'bi-people' }
  ];

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    if (!this.authService.esAdmin()) {
      this.router.navigate(['/dashboard']);
    }
  }
}
