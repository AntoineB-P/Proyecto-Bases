import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: false,
  selector: 'app-barrasuperior',
  templateUrl: './barrasuperior.html',
  styleUrls: ['./barrasuperior.css']
})
export class BarraSuperior {

  constructor(private authService: AuthService, private router: Router) {}

  get logueado(): boolean {
    return this.authService.estaLogueado();
  }

  get iniciales(): string {
    return this.authService.obtenerUsuario().substring(0, 2).toUpperCase() || 'U';
  }

  cerrarSesion(): void {
    this.authService.cerrarSesion();
    this.router.navigate(['/dashboard']);
  }
}
