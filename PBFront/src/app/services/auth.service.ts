import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioModel } from '../models/usuario.model';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private urlBase = 'http://localhost:8080/usuario';

  constructor(private http: HttpClient) {}

  iniciarSesion(usuario: string, contrasenia: string, correo: string = ''): Observable<string> {
    return this.http.post(`${this.urlBase}/login`, { usuario, contrasenia, correo }, { responseType: 'text' });
  }

  registrarUsuario(datos: UsuarioModel): Observable<string> {
    return this.http.post(`${this.urlBase}/register`, datos, { responseType: 'text' });
  }

  verificarCuenta(correo: string, codigoVerificacion: string): Observable<string> {
    return this.http.post(
      `${this.urlBase}/verificar`,
      { correo, codigo: codigoVerificacion },
      { responseType: 'text' },
    );
  }

  guardarToken(token: string): void {
    localStorage.setItem('token', token);
  }

  obtenerToken(): string | null {
    return localStorage.getItem('token');
  }

  guardarUsuario(nombre: string): void {
    localStorage.setItem('usuarioNombre', nombre);
  }

  obtenerUsuario(): string {
    return localStorage.getItem('usuarioNombre') || '';
  }

  cerrarSesion(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('usuarioNombre');
  }

  estaLogueado(): boolean {
    return !!this.obtenerToken();
  }

  obtenerRol(): string {
    const token = this.obtenerToken();
    if (!token) return '';
    try {
      return JSON.parse(atob(token.split('.')[1])).role || '';
    } catch {
      return '';
    }
  }

  esAdmin(): boolean {
    return this.obtenerRol() === 'ADMIN';
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

  validarCorreo(correo: string): string {
    if (!correo || correo.includes(' ')) {
      return 'El correo no puede estar vacío ni contener espacios.';
    }
    const partes = correo.split('@');
    if (partes.length !== 2 || !partes[0] || !/^[A-Za-z0-9.]+$/.test(partes[0])) {
      return 'El correo no es válido. Antes del @ usa solo letras, números y puntos.';
    }
    const dominio = partes[1];
    const permitido =
      dominio.includes('gmail') ||
      dominio.includes('hotmail') ||
      dominio.includes('outlook') ||
      dominio.includes('yahoo') ||
      dominio.includes('unbosque.edu.co');
    if (!permitido || !dominio.includes('.')) {
      return 'El correo debe ser de Gmail, Hotmail, Outlook, Yahoo o unbosque.edu.co.';
    }
    return '';
  }
}
