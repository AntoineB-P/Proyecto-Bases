package co.edu.unbosque.proyectobases.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.JugadorDTO;
import co.edu.unbosque.proyectobases.entity.Jugador;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.JugadorRepository;

@Service
public class JugadorService {

	private static final String[] POSICIONES = { "Portero", "Defensa", "Mediocampista", "Atacante" };

	private final JugadorRepository jugadorRepository;

	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}

	private JugadorDTO aDTO(Jugador j) {
		JugadorDTO dto = new JugadorDTO();
		dto.setIdJugador(j.getIdJugador());
		dto.setNombres(j.getNombres());
		dto.setApellidos(j.getApellidos());
		dto.setNacionalidad(j.getNacionalidad());
		dto.setFechaNacimiento(j.getFechaNacimiento());
		dto.setEdad(Period.between(j.getFechaNacimiento(), LocalDate.now()).getYears());
		dto.setPosicion(j.getPosicion());
		if (j.getPosicion().equals("Atacante")) {
			dto.setTirosPuerta(j.getEstadistica());
		} else if (j.getPosicion().equals("Mediocampista")) {
			dto.setPases(j.getEstadistica());
		} else if (j.getPosicion().equals("Defensa")) {
			dto.setEntradasOportunas(j.getEstadistica());
		} else if (j.getPosicion().equals("Portero")) {
			dto.setPorteriaImbatida(j.getEstadistica());
		}
		return dto;
	}

	private Jugador aEntity(JugadorDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del jugador son obligatorios");
		}
		Jugador j = new Jugador();
		j.setNombres(Validador.texto(dto.getNombres(), "nombres", 80));
		j.setApellidos(Validador.texto(dto.getApellidos(), "apellidos", 80));
		j.setNacionalidad(Validador.texto(dto.getNacionalidad(), "nacionalidad", 50));
		Validador.fechaNoFutura(dto.getFechaNacimiento(), "fecha de nacimiento");
		j.setFechaNacimiento(dto.getFechaNacimiento());
		j.setPosicion(Validador.opcion(dto.getPosicion(), "posición", POSICIONES));
		Integer estadistica = dto.getTirosPuerta();
		if (j.getPosicion().equals("Mediocampista")) {
			estadistica = dto.getPases();
		} else if (j.getPosicion().equals("Defensa")) {
			estadistica = dto.getEntradasOportunas();
		} else if (j.getPosicion().equals("Portero")) {
			estadistica = dto.getPorteriaImbatida();
		}
		if (estadistica != null && estadistica < 0) {
			throw new DatoInvalidoException("La estadística del jugador no puede ser negativa");
		}
		j.setEstadistica(estadistica);
		return j;
	}

	private List<JugadorDTO> aDTOs(List<Jugador> jugadores) {
		List<JugadorDTO> lista = new ArrayList<>();
		for (Jugador j : jugadores) {
			lista.add(aDTO(j));
		}
		return lista;
	}

	public List<JugadorDTO> listar() {
		return aDTOs(jugadorRepository.listar());
	}

	public List<JugadorDTO> listarPorEquipoYTorneo(Long idEquipo, Long idTorneo) {
		return aDTOs(jugadorRepository.listarPorEquipoYTorneo(idEquipo, idTorneo));
	}

	public List<JugadorDTO> listarPorPosicion(String posicion) {
		String valida = Validador.opcion(posicion, "posición", POSICIONES);
		return aDTOs(jugadorRepository.listarPorPosicion(valida));
	}

	public JugadorDTO buscarPorId(Long id) {
		Jugador j = jugadorRepository.buscarPorId(id);
		if (j == null) {
			throw new RecursoNoEncontradoException("No existe el jugador con id " + id);
		}
		return aDTO(j);
	}

	public JugadorDTO crear(JugadorDTO dto) {
		Jugador j = aEntity(dto);
		j.setIdJugador(jugadorRepository.siguienteId());
		jugadorRepository.insertar(j);
		return buscarPorId(j.getIdJugador());
	}

	public JugadorDTO actualizar(Long id, JugadorDTO dto) {
		buscarPorId(id);
		Jugador j = aEntity(dto);
		j.setIdJugador(id);
		jugadorRepository.actualizar(j);
		return buscarPorId(id);
	}

	public void eliminar(Long id) {
		if (jugadorRepository.eliminar(id) == 0) {
			throw new RecursoNoEncontradoException("No existe el jugador con id " + id);
		}
	}
}