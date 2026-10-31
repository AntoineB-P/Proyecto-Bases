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
    return this.http.post(`${this.urlBase}/verificar`, { correo, codigoVerificacion }, { responseType: 'text' });
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
    localStorage.removeItem('clienteId');
    localStorage.removeItem('usuarioNombre');
  }

  obtenerIdDesdeToken(): number {
    const token = this.obtenerToken();
    if (!token) return 0;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.id || payload.clienteId || 0;
    } catch {
      return 0;
    }
  }

  guardarClienteId(id: number): void {
    localStorage.setItem('clienteId', String(id));
  }

  obtenerClienteId(): number {
    return Number(localStorage.getItem('clienteId')) || 0;
  }

  estaLogueado(): boolean {
    return !!this.obtenerToken();
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
