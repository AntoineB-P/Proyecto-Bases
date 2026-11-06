import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  standalone: false,
  selector: 'app-registro',
  templateUrl: './registro.html',
  styleUrls: ['./registro.css']
})
export class Registro implements OnInit {

  formulario = { usuario: '', contrasenia: '', correo: '', codigoVerificacion: '' };
  paso = 1;
  mostrarContrasenia = false;
  cargando = false;
  mensajeError = '';
  mensajeExito = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['verificar'] === 'true') {
        this.paso = 2;
        this.mensajeExito = 'Escribe tu correo y el código que recibiste.';
        this.cdr.detectChanges();
      }
    });
  }

  registrar(form: NgForm): void {
    if (form.invalid) {
      this.mensajeError = 'Completa usuario, correo y contraseña.';
      this.cdr.detectChanges();
      return;
    }

    if (
      this.formulario.usuario.length < 5 ||
      !/^[A-Za-zÁÉÍÓÚáéíóúÑñ]+( [A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$/.test(this.formulario.usuario)
    ) {
      this.mensajeError = 'El usuario debe tener mínimo 5 letras, sin números ni símbolos, y un solo espacio entre palabras.';
      this.cdr.detectChanges();
      return;
    }

    if (this.formulario.contrasenia.length < 12) {
      this.mensajeError = 'La contraseña debe tener mínimo 12 caracteres.';
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

    this.authService.registrarUsuario(this.formulario).subscribe({
      next: () => {
        this.cargando = false;
        this.mensajeExito = 'Te enviamos un código al correo. Escríbelo para verificar tu cuenta.';
        this.paso = 2;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.cargando = false;
        this.mensajeError = err.status === 409
          ? 'Ese usuario o correo ya está registrado. Inicia sesión o usa otro.'
          : 'No se pudo crear la cuenta. Intenta de nuevo.';
        this.cdr.detectChanges();
      }
    });
  }

  verificar(): void {
    const errorCorreo = this.authService.validarCorreo(this.formulario.correo);
    if (errorCorreo) {
      this.mensajeError = errorCorreo;
      this.cdr.detectChanges();
      return;
    }

    this.mensajeError = '';
    this.cargando = true;

    this.authService.verificarCuenta(this.formulario.correo, this.formulario.codigoVerificacion).subscribe({
      next: () => {
        this.cargando = false;
        this.mensajeExito = 'Cuenta verificada. Te llevamos a iniciar sesión.';
        this.cdr.detectChanges();
        setTimeout(() => this.router.navigate(['/login']), 1000);
      },
      error: () => {
        this.cargando = false;
        this.mensajeError = 'El código no es válido. Revisa tu correo e intenta de nuevo.';
        this.cdr.detectChanges();
      }
    });
  }
}
