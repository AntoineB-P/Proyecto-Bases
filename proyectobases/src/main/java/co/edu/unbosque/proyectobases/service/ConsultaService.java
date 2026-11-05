package co.edu.unbosque.proyectobases.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.EstadisticaJugadorDTO;
import co.edu.unbosque.proyectobases.dto.HistorialEquipoDTO;
import co.edu.unbosque.proyectobases.dto.PosicionDTO;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.ConsultaRepository;
import co.edu.unbosque.proyectobases.repository.EquipoRepository;
import co.edu.unbosque.proyectobases.repository.EquipoTorneoRepository;
import co.edu.unbosque.proyectobases.repository.TorneoRepository;

@Service
public class ConsultaService {

	private final ConsultaRepository consultaRepository;
	private final TorneoRepository torneoRepository;
	private final EquipoRepository equipoRepository;
	private final EquipoTorneoRepository equipoTorneoRepository;

	public ConsultaService(ConsultaRepository consultaRepository, TorneoRepository torneoRepository,
			EquipoRepository equipoRepository, EquipoTorneoRepository equipoTorneoRepository) {
		this.consultaRepository = consultaRepository;
		this.torneoRepository = torneoRepository;
		this.equipoRepository = equipoRepository;
		this.equipoTorneoRepository = equipoTorneoRepository;
	}

	private void validarTorneo(Long idTorneo) {
		if (torneoRepository.buscarPorId(idTorneo) == null) {
			throw new RecursoNoEncontradoException("No existe el torneo con id " + idTorneo);
		}
	}

	public List<PosicionDTO> tablaPosiciones(Long idTorneo) {
		validarTorneo(idTorneo);
		return consultaRepository.tablaPosiciones(idTorneo);
	}

	public List<HistorialEquipoDTO> historialEquipo(Long idEquipo, Long idTorneo) {
		validarTorneo(idTorneo);
		if (equipoRepository.buscarPorId(idEquipo) == null) {
			throw new RecursoNoEncontradoException("No existe el equipo con id " + idEquipo);
		}
		if (equipoTorneoRepository.buscar(idEquipo, idTorneo) == null) {
			throw new DatoInvalidoException("Ese equipo no participa en ese torneo");
		}
		return consultaRepository.historialEquipo(idEquipo, idTorneo);
	}

	public List<EstadisticaJugadorDTO> estadisticasJugadores(Long idTorneo) {
		validarTorneo(idTorneo);
		return consultaRepository.estadisticasJugadores(idTorneo);
	}
}