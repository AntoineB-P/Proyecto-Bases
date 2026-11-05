package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.EquipoTorneoDTO;
import co.edu.unbosque.proyectobases.entity.Equipo;
import co.edu.unbosque.proyectobases.entity.EquipoTorneo;
import co.edu.unbosque.proyectobases.entity.Torneo;
import co.edu.unbosque.proyectobases.exception.ConflictoException;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.EquipoRepository;
import co.edu.unbosque.proyectobases.repository.EquipoTorneoRepository;
import co.edu.unbosque.proyectobases.repository.TorneoRepository;

@Service
public class EquipoTorneoService {

	private final EquipoTorneoRepository equipoTorneoRepository;
	private final EquipoRepository equipoRepository;
	private final TorneoRepository torneoRepository;

	public EquipoTorneoService(EquipoTorneoRepository equipoTorneoRepository, EquipoRepository equipoRepository,
			TorneoRepository torneoRepository) {
		this.equipoTorneoRepository = equipoTorneoRepository;
		this.equipoRepository = equipoRepository;
		this.torneoRepository = torneoRepository;
	}

	private EquipoTorneoDTO aDTO(EquipoTorneo et) {
		EquipoTorneoDTO dto = new EquipoTorneoDTO();
		dto.setIdEquipo(et.getIdEquipo());
		dto.setIdTorneo(et.getIdTorneo());
		Equipo e = equipoRepository.buscarPorId(et.getIdEquipo());
		dto.setNombreEquipo(e == null ? null : e.getNombreEquipo());
		Torneo t = torneoRepository.buscarPorId(et.getIdTorneo());
		dto.setNombreTorneo(t == null ? null : t.getNombreTorneo());
		return dto;
	}

	private List<EquipoTorneoDTO> aDTOs(List<EquipoTorneo> lista) {
		List<EquipoTorneoDTO> dtos = new ArrayList<>();
		for (EquipoTorneo et : lista) {
			dtos.add(aDTO(et));
		}
		return dtos;
	}

	public List<EquipoTorneoDTO> listarPorTorneo(Long idTorneo) {
		return aDTOs(equipoTorneoRepository.listarPorTorneo(idTorneo));
	}

	public List<EquipoTorneoDTO> listarPorEquipo(Long idEquipo) {
		return aDTOs(equipoTorneoRepository.listarPorEquipo(idEquipo));
	}

	public EquipoTorneoDTO crear(EquipoTorneoDTO dto) {
		if (dto == null || dto.getIdEquipo() == null || dto.getIdTorneo() == null) {
			throw new DatoInvalidoException("El equipo y el torneo son obligatorios");
		}
		if (equipoRepository.buscarPorId(dto.getIdEquipo()) == null) {
			throw new DatoInvalidoException("El equipo no existe");
		}
		if (torneoRepository.buscarPorId(dto.getIdTorneo()) == null) {
			throw new DatoInvalidoException("El torneo no existe");
		}
		if (equipoTorneoRepository.buscar(dto.getIdEquipo(), dto.getIdTorneo()) != null) {
			throw new ConflictoException("El equipo ya está inscrito en ese torneo");
		}
		EquipoTorneo et = new EquipoTorneo();
		et.setIdEquipo(dto.getIdEquipo());
		et.setIdTorneo(dto.getIdTorneo());
		equipoTorneoRepository.insertar(et);
		return aDTO(et);
	}

	public void eliminar(Long idEquipo, Long idTorneo) {
		if (equipoTorneoRepository.eliminar(idEquipo, idTorneo) == 0) {
			throw new RecursoNoEncontradoException("Ese equipo no está inscrito en ese torneo");
		}
	}
}