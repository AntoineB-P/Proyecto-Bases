import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { AuthService } from '../../services/auth.service';
import { UsuarioModel } from '../../models/usuario.model';

@Component({
  standalone: false,
  selector: 'adminusuarios',
  templateUrl: './adminusuarios.component.html'
})
export class AdminUsuariosComponent implements OnInit {

  usuarios: UsuarioModel[] = [];
  miUsuario = '';
  cargando = true;
  mensajeError = '';
  mensajeExito = '';

  constructor(
    private usuarioService: UsuarioService,
    private authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.miUsuario = this.authService.obtenerUsuario();
    this.cargar();
  }

  cargar(): void {
    this.usuarioService.getTodos().subscribe({
      next: (lista) => {
        this.usuarios = lista;
        this.cargando = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.mensajeError = this.leerError(err);
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
  }

  cambiarRol(usuario: UsuarioModel): void {
    let nuevoRol = 'ADMIN';
    if (usuario.rol === 'ADMIN') {
      nuevoRol = 'USUARIO';
    }
    if (!confirm('¿Cambiar a ' + usuario.usuario + ' al rol ' + nuevoRol + '?')) {
      return;
    }
    this.usuarioService.putCambiarRol(usuario.idUsuario || 0, nuevoRol).subscribe({
      next: () => {
        this.mensajeExito = 'Rol actualizado. ' + usuario.usuario + ' debe cerrar sesión y volver a entrar.';
        this.cargar();
      },
      error: (err) => {
        this.mensajeError = this.leerError(err);
        this.cdr.detectChanges();
      }
    });
  }

  leerError(err: any): string {
    if (err.status === 401 || err.status === 403) {
      return 'Tu sesión expiró o no eres administrador. Inicia sesión de nuevo.';
    }
    if (err.error && err.error.error) {
      return err.error.error;
    }
    return 'No se pudo completar la acción.';
  }
}
