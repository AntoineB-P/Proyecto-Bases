import { Component, ChangeDetectorRef } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  standalone: false,
  selector: 'app-login',
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {

  formulario = { usuario: '', contrasenia: '', correo: '' };
  mostrarContrasenia = false;
  cargando = false;
  mensajeError = '';
  mensajeExito = '';

  constructor(
    private router: Router,
    private authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  enviarFormulario(form: NgForm): void {
    if (form.invalid) {
      this.mensajeError = 'Completa usuario, correo y contraseña.';
      this.cdr.detectChanges();
      return;
    }

    const errorCorreo = this.authService.validarCorreo(this.formulario.correo);
    if (errorCorreo) {
      this.mensajeError = errorCorreo;
      this.cdr.detectChanges();
      return;
    }

    this.mensajeError = '';
    this.cargando = true;

    this.authService.iniciarSesion(this.formulario.usuario, this.formulario.contrasenia, this.formulario.correo).subscribe({
      next: (token) => {
        this.authService.guardarToken(token);
        this.authService.guardarClienteId(this.authService.obtenerIdDesdeToken());
        this.authService.guardarUsuario(this.formulario.usuario);
        this.mensajeExito = 'Sesión iniciada.';
        this.cargando = false;
        this.cdr.detectChanges();
        setTimeout(() => this.router.navigate(['/dashboard']), 800);
      },
      error: () => {
        this.mensajeError = 'Usuario, correo o contraseña incorrectos. Revisa los datos e intenta de nuevo.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }
}
