package co.edu.unbosque.proyectobases.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Usuario implements UserDetails {

    private Long idUsuario;
    private String usuario;
    private String contrasenia;
    private Rol rol;
    private String correo;
    private Long idEquipoFavorito;
    
    private boolean verificado;
    private String codigoVerificacion;
    private LocalDateTime expiraVerificacion; 

    public Usuario() {
    }

   

    public Usuario(Long idUsuario, String usuario, String contrasenia, Rol rol, String correo, Long idEquipoFavorito,
			boolean verificado, String codigoVerificacion, LocalDateTime expiraVerificacion) {
		super();
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.rol = rol;
		this.correo = correo;
		this.idEquipoFavorito = idEquipoFavorito;
		this.verificado = verificado;
		this.codigoVerificacion = codigoVerificacion;
		this.expiraVerificacion = expiraVerificacion;
	}



	public boolean isVerificado() {
		return verificado;
	}



	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}



	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}



	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}



	public LocalDateTime getExpiraVerificacion() {
		return expiraVerificacion;
	}



	public void setExpiraVerificacion(LocalDateTime expiraVerificacion) {
		this.expiraVerificacion = expiraVerificacion;
	}



	public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getIdEquipoFavorito() {
        return idEquipoFavorito;
    }

    public void setIdEquipoFavorito(Long idEquipoFavorito) {
        this.idEquipoFavorito = idEquipoFavorito;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (rol == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}