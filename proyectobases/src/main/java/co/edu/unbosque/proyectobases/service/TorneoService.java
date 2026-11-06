package co.edu.unbosque.proyectobases.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.TorneoDTO;
import co.edu.unbosque.proyectobases.entity.Torneo;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.TorneoRepository;

@Service
public class TorneoService {

	private final TorneoRepository torneoRepository;

	public TorneoService(TorneoRepository torneoRepository) {
		this.torneoRepository = torneoRepository;
	}

	private TorneoDTO aDTO(Torneo t) {
		TorneoDTO dto = new TorneoDTO();
		dto.setIdTorneo(t.getIdTorneo());
		dto.setNombre(t.getNombreTorneo());
		dto.setAnio(t.getAnio());
		dto.setSemestre(t.getSemestre());
		dto.setFechaInicio(t.getFechaInicioTorneo());
		dto.setFechaFin(t.getFechaFinTorneo());
		dto.setDuracionDias((int) ChronoUnit.DAYS.between(t.getFechaInicioTorneo(), t.getFechaFinTorneo()));
		return dto;
	}

	private Torneo aEntity(TorneoDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del torneo son obligatorios");
		}
		Validador.rango(dto.getAnio(), "año", 1900, 2100);
		Validador.rango(dto.getSemestre(), "semestre", 1, 2);
		Validador.obligatorio(dto.getFechaInicio(), "fecha de inicio");
		Validador.obligatorio(dto.getFechaFin(), "fecha de fin");
		if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
			throw new DatoInvalidoException("La fecha de fin no puede ser anterior a la de inicio");
		}
		Torneo t = new Torneo();
		t.setNombreTorneo(Validador.texto(dto.getNombre(), "nombre", 100));
		t.setAnio(dto.getAnio());
		t.setSemestre(dto.getSemestre());
		t.setFechaInicioTorneo(dto.getFechaInicio());
		t.setFechaFinTorneo(dto.getFechaFin());
		return t;
	}

	public List<TorneoDTO> listar() {
		List<TorneoDTO> lista = new ArrayList<>();
		for (Torneo t : torneoRepository.listar()) {
			lista.add(aDTO(t));
		}
		return lista;
	}

	public TorneoDTO buscarPorId(Long id) {
		Torneo t = torneoRepository.buscarPorId(id);
		if (t == null) {
			throw new RecursoNoEncontradoException("No existe el torneo con id " + id);
		}
		return aDTO(t);
	}

	public TorneoDTO crear(TorneoDTO dto) {
		Torneo t = aEntity(dto);
		t.setIdTorneo(torneoRepository.siguienteId());
		torneoRepository.insertar(t);
		return buscarPorId(t.getIdTorneo());
	}

	public TorneoDTO actualizar(Long id, TorneoDTO dto) {
		buscarPorId(id);
		Torneo t = aEntity(dto);
		t.setIdTorneo(id);
		torneoRepository.actualizar(t);
		return buscarPorId(id);
	}

	public void eliminar(Long id) {
		if (torneoRepository.eliminar(id) == 0) {
			throw new RecursoNoEncontradoException("No existe el torneo con id " + id);
		}
	}
}