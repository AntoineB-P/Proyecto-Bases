package co.edu.unbosque.proyectobases.dto;

public class VerificacionDTO {

    private String correo;
    private String codigo;

    public VerificacionDTO() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}