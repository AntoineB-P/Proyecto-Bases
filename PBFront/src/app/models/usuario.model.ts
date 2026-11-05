export interface UsuarioModel {
  idUsuario?: number;
  rol?: string;
  usuario: string;
  contrasenia: string;
  correo: string;
  verificado?: boolean;
  codigoVerificacion?: string;
}
