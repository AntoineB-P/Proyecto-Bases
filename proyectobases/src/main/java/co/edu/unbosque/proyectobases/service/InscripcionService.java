package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.InscripcionDTO;
import co.edu.unbosque.proyectobases.entity.Equipo;
import co.edu.unbosque.proyectobases.entity.Inscripcion;
import co.edu.unbosque.proyectobases.entity.Jugador;
import co.edu.unbosque.proyectobases.exception.ConflictoException;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.EquipoRepository;
import co.edu.unbosque.proyectobases.repository.EquipoTorneoRepository;
import co.edu.unbosque.proyectobases.repository.InscripcionRepository;
import co.edu.unbosque.proyectobases.repository.JugadorRepository;

@Service
public class InscripcionService {

	private final InscripcionRepository inscripcionRepository;
	private final EquipoRepository equipoRepository;
	private final JugadorRepository jugadorRepository;
	private final EquipoTorneoRepository equipoTorneoRepository;

	public InscripcionService(InscripcionRepository inscripcionRepository, EquipoRepository equipoRepository,
			JugadorRepository jugadorRepository, EquipoTorneoRepository equipoTorneoRepository) {
		this.inscripcionRepository = inscripcionRepository;
		this.equipoRepository = equipoRepository;
		this.jugadorRepository = jugadorRepository;
		this.equipoTorneoRepository = equipoTorneoRepository;
	}

	private InscripcionDTO aDTO(Inscripcion i) {
		InscripcionDTO dto = new InscripcionDTO();
		dto.setIdEquipo(i.getIdEquipo());
		dto.setIdTorneo(i.getIdTorneo());
		dto.setIdJugador(i.getIdJugador());
		dto.setDorsal(i.getDorsal());
		dto.setFechaInicio(i.getFechaInicio());
		dto.setFechaFin(i.getFechaFin());
		Equipo e = equipoRepository.buscarPorId(i.getIdEquipo());
		dto.setNombreEquipo(e == null ? null : e.getNombreEquipo());
		Jugador j = jugadorRepository.buscarPorId(i.getIdJugador());
		dto.setNombreJugador(j == null ? null : j.getNombres() + " " + j.getApellidos());
		return dto;
	}

	private List<InscripcionDTO> aDTOs(List<Inscripcion> lista) {
		List<InscripcionDTO> dtos = new ArrayList<>();
		for (Inscripcion i : lista) {
			dtos.add(aDTO(i));
		}
		return dtos;
	}

	/** Valida los campos propios (sin mirar la BD) y arma la entity. */
	private Inscripcion aEntity(InscripcionDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos de la inscripción son obligatorios");
		}
		Validador.rango(dto.getDorsal(), "dorsal", 1, 99);
		Validador.obligatorio(dto.getFechaInicio(), "fecha de inicio");
		if (dto.getFechaFin() != null && dto.getFechaFin().isBefore(dto.getFechaInicio())) {
			throw new DatoInvalidoException("La fecha de fin no puede ser anterior a la de inicio");
		}
		Inscripcion i = new Inscripcion();
		i.setDorsal(dto.getDorsal());
		i.setFechaInicio(dto.getFechaInicio());
		i.setFechaFin(dto.getFechaFin()); // puede ser null
		return i;
	}

	/** El dorsal no puede repetirse en el mismo equipo y torneo (otro jugador). */
	private void validarDorsal(Long idEquipo, Long idTorneo, Long idJugador, Integer dorsal) {
		for (Inscripcion otra : inscripcionRepository.listarPorEquipoYTorneo(idEquipo, idTorneo)) {
			if (otra.getDorsal().equals(dorsal) && !otra.getIdJugador().equals(idJugador)) {
				throw new ConflictoException("El dorsal " + dorsal + " ya lo tiene otro jugador del equipo");
			}
		}
	}

	public List<InscripcionDTO> listarPorEquipoYTorneo(Long idEquipo, Long idTorneo) {
		return aDTOs(inscripcionRepository.listarPorEquipoYTorneo(idEquipo, idTorneo));
	}

	public List<InscripcionDTO> listarPorJugador(Long idJugador) {
		return aDTOs(inscripcionRepository.listarPorJugador(idJugador));
	}

	public InscripcionDTO buscar(Long idEquipo, Long idTorneo, Long idJugador) {
		Inscripcion i = inscripcionRepository.buscar(idEquipo, idTorneo, idJugador);
		if (i == null) {
			throw new RecursoNoEncontradoException("No existe esa inscripción");
		}
		return aDTO(i);
	}

	public InscripcionDTO crear(InscripcionDTO dto) {
		Inscripcion i = aEntity(dto);
		Validador.obligatorio(dto.getIdEquipo(), "equipo");
		Validador.obligatorio(dto.getIdTorneo(), "torneo");
		Validador.obligatorio(dto.getIdJugador(), "jugador");
		i.setIdEquipo(dto.getIdEquipo());
		i.setIdTorneo(dto.getIdTorneo());
		i.setIdJugador(dto.getIdJugador());

		if (equipoTorneoRepository.buscar(i.getIdEquipo(), i.getIdTorneo()) == null) {
			throw new DatoInvalidoException("El equipo no está inscrito en ese torneo");
		}
		if (jugadorRepository.buscarPorId(i.getIdJugador()) == null) {
			throw new DatoInvalidoException("El jugador no existe");
		}
		// Un jugador solo puede estar en un equipo por torneo
		for (Inscripcion otra : inscripcionRepository.listarPorJugador(i.getIdJugador())) {
			if (otra.getIdTorneo().equals(i.getIdTorneo())) {
				throw new ConflictoException("El jugador ya está inscrito en ese torneo");
			}
		}
		validarDorsal(i.getIdEquipo(), i.getIdTorneo(), i.getIdJugador(), i.getDorsal());

		inscripcionRepository.insertar(i);
		return buscar(i.getIdEquipo(), i.getIdTorneo(), i.getIdJugador());
	}

	/** Se pueden cambiar el dorsal y las fechas. */
	public InscripcionDTO actualizar(Long idEquipo, Long idTorneo, Long idJugador, InscripcionDTO dto) {
		buscar(idEquipo, idTorneo, idJugador);
		Inscripcion i = aEntity(dto);
		i.setIdEquipo(idEquipo);
		i.setIdTorneo(idTorneo);
		i.setIdJugador(idJugador);
		validarDorsal(idEquipo, idTorneo, idJugador, i.getDorsal());

		inscripcionRepository.actualizar(i);
		return buscar(idEquipo, idTorneo, idJugador);
	}

	public void eliminar(Long idEquipo, Long idTorneo, Long idJugador) {
		if (inscripcionRepository.eliminar(idEquipo, idTorneo, idJugador) == 0) {
			throw new RecursoNoEncontradoException("No existe esa inscripción");
		}
	}
}