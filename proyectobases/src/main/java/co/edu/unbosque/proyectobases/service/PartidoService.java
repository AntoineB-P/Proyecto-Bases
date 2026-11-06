package co.edu.unbosque.proyectobases.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.PartidoDTO;
import co.edu.unbosque.proyectobases.entity.Equipo;
import co.edu.unbosque.proyectobases.entity.Estadio;
import co.edu.unbosque.proyectobases.entity.Partido;
import co.edu.unbosque.proyectobases.entity.Resultado;
import co.edu.unbosque.proyectobases.exception.ConflictoException;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.EquipoRepository;
import co.edu.unbosque.proyectobases.repository.EquipoTorneoRepository;
import co.edu.unbosque.proyectobases.repository.EstadioRepository;
import co.edu.unbosque.proyectobases.repository.ParticipacionRepository;
import co.edu.unbosque.proyectobases.repository.PartidoRepository;
import co.edu.unbosque.proyectobases.repository.ResultadoRepository;
import co.edu.unbosque.proyectobases.repository.TorneoRepository;

@Service
public class PartidoService {

	// La tabla guarda la hora como texto "HH:MM"
	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	private final PartidoRepository partidoRepository;
	private final EstadioRepository estadioRepository;
	private final EquipoRepository equipoRepository;
	private final TorneoRepository torneoRepository;
	private final EquipoTorneoRepository equipoTorneoRepository;
	private final ParticipacionRepository participacionRepository;
	private final ResultadoRepository resultadoRepository;

	public PartidoService(PartidoRepository partidoRepository, EstadioRepository estadioRepository,
			EquipoRepository equipoRepository, TorneoRepository torneoRepository,
			EquipoTorneoRepository equipoTorneoRepository, ParticipacionRepository participacionRepository,
			ResultadoRepository resultadoRepository) {
		this.partidoRepository = partidoRepository;
		this.estadioRepository = estadioRepository;
		this.equipoRepository = equipoRepository;
		this.torneoRepository = torneoRepository;
		this.equipoTorneoRepository = equipoTorneoRepository;
		this.participacionRepository = participacionRepository;
		this.resultadoRepository = resultadoRepository;
	}

	// ---------------- conversión entity <-> DTO ----------------

	private PartidoDTO aDTO(Partido p) {
		PartidoDTO dto = new PartidoDTO();
		dto.setIdPartido(p.getIdPartido());
		dto.setIdTorneo(p.getIdTorneo());
		dto.setFecha(p.getFecha());
		dto.setHora(LocalTime.parse(p.getHora())); // "19:30" -> LocalTime
		dto.setJornada(p.getJornada());
		dto.setIdEstadio(p.getIdEstadio());
		dto.setIdLocal(p.getIdLocal());
		dto.setIdVisitante(p.getIdVisitante());

		Estadio estadio = estadioRepository.buscarPorId(p.getIdEstadio());
		dto.setNombreEstadio(estadio == null ? null : estadio.getNombreEstadio());

		Equipo local = equipoRepository.buscarPorId(p.getIdLocal());
		dto.setNombreLocal(local == null ? null : local.getNombreEquipo());

		Equipo visitante = equipoRepository.buscarPorId(p.getIdVisitante());
		dto.setNombreVisitante(visitante == null ? null : visitante.getNombreEquipo());

		// Si el partido no se ha jugado no hay resultado: los goles quedan en null
		Resultado r = resultadoRepository.buscarPorPartido(p.getIdPartido());
		if (r != null) {
			dto.setGolesLocal(r.getGolesLocal());
			dto.setGolesVisitante(r.getGolesVisitante());
		}
		return dto;
	}

	private Partido aEntity(PartidoDTO dto) {
		Partido p = new Partido();
		p.setIdPartido(dto.getIdPartido());
		p.setFecha(dto.getFecha());
		p.setHora(dto.getHora().format(FORMATO_HORA)); // LocalTime -> "19:30"
		p.setJornada(dto.getJornada());
		p.setIdTorneo(dto.getIdTorneo());
		p.setIdEstadio(dto.getIdEstadio());
		p.setIdLocal(dto.getIdLocal());
		p.setIdVisitante(dto.getIdVisitante());
		return p;
	}

	private List<PartidoDTO> aDTOs(List<Partido> partidos) {
		List<PartidoDTO> lista = new ArrayList<>();
		for (Partido p : partidos) {
			lista.add(aDTO(p));
		}
		return lista;
	}

	// ---------------- validaciones ----------------

	/** idPartidoActual es null al crear, y el id del partido al actualizar. */
	private void validar(PartidoDTO dto, Long idPartidoActual) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del partido son obligatorios");
		}
		if (dto.getFecha() == null) {
			throw new DatoInvalidoException("La fecha del partido es obligatoria");
		}
		if (dto.getHora() == null) {
			throw new DatoInvalidoException("La hora del partido es obligatoria");
		}
		if (dto.getJornada() == null || dto.getJornada() <= 0) {
			throw new DatoInvalidoException("La jornada debe ser un número mayor que 0");
		}
		if (dto.getIdTorneo() == null || torneoRepository.buscarPorId(dto.getIdTorneo()) == null) {
			throw new DatoInvalidoException("El torneo no existe");
		}
		if (dto.getIdEstadio() == null || estadioRepository.buscarPorId(dto.getIdEstadio()) == null) {
			throw new DatoInvalidoException("El estadio no existe");
		}
		if (dto.getIdLocal() == null || equipoRepository.buscarPorId(dto.getIdLocal()) == null) {
			throw new DatoInvalidoException("El equipo local no existe");
		}
		if (dto.getIdVisitante() == null || equipoRepository.buscarPorId(dto.getIdVisitante()) == null) {
			throw new DatoInvalidoException("El equipo visitante no existe");
		}
		if (dto.getIdLocal().equals(dto.getIdVisitante())) {
			throw new DatoInvalidoException("El equipo local y el visitante no pueden ser el mismo");
		}
		if (equipoTorneoRepository.buscar(dto.getIdLocal(), dto.getIdTorneo()) == null) {
			throw new DatoInvalidoException("El equipo local no está inscrito en ese torneo");
		}
		if (equipoTorneoRepository.buscar(dto.getIdVisitante(), dto.getIdTorneo()) == null) {
			throw new DatoInvalidoException("El equipo visitante no está inscrito en ese torneo");
		}

		// Un equipo no puede jugar dos partidos en la misma jornada del mismo torneo
		for (Partido otro : partidoRepository.listarPorJornada(dto.getIdTorneo(), dto.getJornada())) {
			if (idPartidoActual != null && otro.getIdPartido().equals(idPartidoActual)) {
				continue; // al actualizar, ignorar el propio partido
			}
			boolean repiteLocal = dto.getIdLocal().equals(otro.getIdLocal())
					|| dto.getIdLocal().equals(otro.getIdVisitante());
			boolean repiteVisitante = dto.getIdVisitante().equals(otro.getIdLocal())
					|| dto.getIdVisitante().equals(otro.getIdVisitante());
			if (repiteLocal || repiteVisitante) {
				throw new ConflictoException("Uno de los equipos ya juega en la jornada " + dto.getJornada());
			}
		}
	}

	// ---------------- operaciones ----------------

	public List<PartidoDTO> listar() {
		return aDTOs(partidoRepository.listar());
	}

	public List<PartidoDTO> listarPorTorneo(Long idTorneo) {
		return aDTOs(partidoRepository.listarPorTorneo(idTorneo));
	}

	public List<PartidoDTO> listarPorJornada(Long idTorneo, Integer jornada) {
		return aDTOs(partidoRepository.listarPorJornada(idTorneo, jornada));
	}

	public List<PartidoDTO> listarPorEquipo(Long idEquipo) {
		return aDTOs(partidoRepository.listarPorEquipo(idEquipo));
	}

	public PartidoDTO buscarPorId(Long id) {
		Partido p = partidoRepository.buscarPorId(id);
		if (p == null) {
			throw new RecursoNoEncontradoException("No existe el partido con id " + id);
		}
		return aDTO(p);
	}

	public PartidoDTO crear(PartidoDTO dto) {
		validar(dto, null);
		dto.setIdPartido(partidoRepository.siguienteId());
		partidoRepository.insertar(aEntity(dto));
		return buscarPorId(dto.getIdPartido());
	}

	public PartidoDTO actualizar(Long id, PartidoDTO dto) {
		if (partidoRepository.buscarPorId(id) == null) {
			throw new RecursoNoEncontradoException("No existe el partido con id " + id);
		}
		validar(dto, id);
		dto.setIdPartido(id);
		partidoRepository.actualizar(aEntity(dto));
		return buscarPorId(id);
	}

	public void eliminar(Long id) {
		if (partidoRepository.buscarPorId(id) == null) {
			throw new RecursoNoEncontradoException("No existe el partido con id " + id);
		}
		// La FK de participacion no tiene ON DELETE CASCADE: se avisa antes de intentar
		if (!participacionRepository.listarPorPartido(id).isEmpty()) {
			throw new ConflictoException("No se puede eliminar: el partido ya tiene participaciones registradas");
		}
		partidoRepository.eliminar(id); // su resultado y su arbitraje se borran solos
	}
}