package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.ParticipacionDTO;
import co.edu.unbosque.proyectobases.entity.Jugador;
import co.edu.unbosque.proyectobases.entity.Participacion;
import co.edu.unbosque.proyectobases.entity.Partido;
import co.edu.unbosque.proyectobases.exception.ConflictoException;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.InscripcionRepository;
import co.edu.unbosque.proyectobases.repository.JugadorRepository;
import co.edu.unbosque.proyectobases.repository.ParticipacionRepository;
import co.edu.unbosque.proyectobases.repository.PartidoRepository;

@Service
public class ParticipacionService {

	private final ParticipacionRepository participacionRepository;
	private final JugadorRepository jugadorRepository;
	private final PartidoRepository partidoRepository;
	private final InscripcionRepository inscripcionRepository;

	public ParticipacionService(ParticipacionRepository participacionRepository,
			JugadorRepository jugadorRepository, PartidoRepository partidoRepository,
			InscripcionRepository inscripcionRepository) {
		this.participacionRepository = participacionRepository;
		this.jugadorRepository = jugadorRepository;
		this.partidoRepository = partidoRepository;
		this.inscripcionRepository = inscripcionRepository;
	}

	private ParticipacionDTO aDTO(Participacion p) {
		ParticipacionDTO dto = new ParticipacionDTO();
		dto.setIdParticipacion(p.getIdParticipacion());
		dto.setIdJugador(p.getIdJugador());
		dto.setIdPartido(p.getIdPartido());
		dto.setTitular("S".equals(p.getTitular())); // "S"/"N" -> true/false
		dto.setMinutosJugados(p.getMinutosJugados());
		Jugador j = jugadorRepository.buscarPorId(p.getIdJugador());
		dto.setNombreJugador(j == null ? null : j.getNombres() + " " + j.getApellidos());
		return dto;
	}

	private List<ParticipacionDTO> aDTOs(List<Participacion> lista) {
		List<ParticipacionDTO> dtos = new ArrayList<>();
		for (Participacion p : lista) {
			dtos.add(aDTO(p));
		}
		return dtos;
	}

	private void validarTitularYMinutos(ParticipacionDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos de la participación son obligatorios");
		}
		Validador.obligatorio(dto.getTitular(), "titular");
		Validador.rango(dto.getMinutosJugados(), "minutos jugados", 0, 120);
	}

	public ParticipacionDTO buscarPorId(Long id) {
		Participacion p = participacionRepository.buscarPorId(id);
		if (p == null) {
			throw new RecursoNoEncontradoException("No existe la participación con id " + id);
		}
		return aDTO(p);
	}

	public List<ParticipacionDTO> listarPorPartido(Long idPartido) {
		return aDTOs(participacionRepository.listarPorPartido(idPartido));
	}

	public List<ParticipacionDTO> listarPorJugador(Long idJugador) {
		return aDTOs(participacionRepository.listarPorJugador(idJugador));
	}

	public ParticipacionDTO crear(ParticipacionDTO dto) {
		validarTitularYMinutos(dto);
		Validador.obligatorio(dto.getIdJugador(), "jugador");
		Validador.obligatorio(dto.getIdPartido(), "partido");

		Partido partido = partidoRepository.buscarPorId(dto.getIdPartido());
		if (partido == null) {
			throw new DatoInvalidoException("El partido no existe");
		}
		if (jugadorRepository.buscarPorId(dto.getIdJugador()) == null) {
			throw new DatoInvalidoException("El jugador no existe");
		}
		// El jugador debe estar inscrito en el local o en el visitante, en ese torneo
		boolean juegaConLocal = inscripcionRepository.buscar(partido.getIdLocal(), partido.getIdTorneo(),
				dto.getIdJugador()) != null;
		boolean juegaConVisitante = inscripcionRepository.buscar(partido.getIdVisitante(), partido.getIdTorneo(),
				dto.getIdJugador()) != null;
		if (!juegaConLocal && !juegaConVisitante) {
			throw new DatoInvalidoException("El jugador no está inscrito en ninguno de los dos equipos del partido");
		}
		if (participacionRepository.buscarPorJugadorYPartido(dto.getIdJugador(), dto.getIdPartido()) != null) {
			throw new ConflictoException("El jugador ya tiene participación en ese partido");
		}

		Participacion p = new Participacion();
		p.setIdParticipacion(participacionRepository.siguienteId());
		p.setIdJugador(dto.getIdJugador());
		p.setIdPartido(dto.getIdPartido());
		p.setTitular(dto.getTitular() ? "S" : "N");
		p.setMinutosJugados(dto.getMinutosJugados());
		participacionRepository.insertar(p);
		return buscarPorId(p.getIdParticipacion());
	}

	/** Solo se pueden cambiar titular y minutos. */
	public ParticipacionDTO actualizar(Long id, ParticipacionDTO dto) {
		Participacion actual = participacionRepository.buscarPorId(id);
		if (actual == null) {
			throw new RecursoNoEncontradoException("No existe la participación con id " + id);
		}
		validarTitularYMinutos(dto);
		actual.setTitular(dto.getTitular() ? "S" : "N");
		actual.setMinutosJugados(dto.getMinutosJugados());
		participacionRepository.actualizar(actual);
		return buscarPorId(id);
	}

	public void eliminar(Long id) {
		if (participacionRepository.eliminar(id) == 0) {
			throw new RecursoNoEncontradoException("No existe la participación con id " + id);
		}
	}
}