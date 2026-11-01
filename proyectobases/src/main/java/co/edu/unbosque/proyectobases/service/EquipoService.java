package co.edu.unbosque.proyectobases.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.EquipoDTO;
import co.edu.unbosque.proyectobases.entity.Equipo;
import co.edu.unbosque.proyectobases.entity.Estadio;
import co.edu.unbosque.proyectobases.entity.Tecnico;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.EquipoRepository;
import co.edu.unbosque.proyectobases.repository.EstadioRepository;
import co.edu.unbosque.proyectobases.repository.TecnicoRepository;

@Service
public class EquipoService {

	private final EquipoRepository equipoRepository;
	private final EstadioRepository estadioRepository;
	private final TecnicoRepository tecnicoRepository;

	public EquipoService(EquipoRepository equipoRepository, EstadioRepository estadioRepository,
			TecnicoRepository tecnicoRepository) {
		this.equipoRepository = equipoRepository;
		this.estadioRepository = estadioRepository;
		this.tecnicoRepository = tecnicoRepository;
	}

	// ---------------- conversión entity -> DTO ----------------

	private EquipoDTO aDTO(Equipo e) {
		EquipoDTO dto = new EquipoDTO();
		dto.setIdEquipo(e.getIdEquipo());
		dto.setNombre(e.getNombreEquipo());
		dto.setFechaFundacion(e.getFechaFundacion());
		dto.setCiudad(e.getCiudadEquipo());
		dto.setColores(equipoRepository.listarColores(e.getIdEquipo()));
		dto.setIdSede(e.getIdSede());
		dto.setIdTecnico(e.getIdTecnico());

		Estadio estadio = estadioRepository.buscarPorId(e.getIdSede());
		dto.setNombreEstadio(estadio == null ? null : estadio.getNombreEstadio());

		Tecnico tecnico = tecnicoRepository.buscarPorId(e.getIdTecnico());
		dto.setNombreTecnico(tecnico == null ? null : tecnico.getNombres() + " " + tecnico.getApellidos());
		return dto;
	}

	private Equipo aEntity(EquipoDTO dto) {
		Equipo e = new Equipo();
		e.setIdEquipo(dto.getIdEquipo());
		e.setNombreEquipo(dto.getNombre().trim());
		e.setFechaFundacion(dto.getFechaFundacion());
		e.setCiudadEquipo(dto.getCiudad().trim());
		e.setIdTecnico(dto.getIdTecnico());
		e.setIdSede(dto.getIdSede());
		return e;
	}

	// ---------------- validaciones ----------------

	private void validar(EquipoDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del equipo son obligatorios");
		}
		if (dto.getNombre() == null || dto.getNombre().isBlank()) {
			throw new DatoInvalidoException("El nombre del equipo es obligatorio");
		}
		if (dto.getNombre().trim().length() > 100) {
			throw new DatoInvalidoException("El nombre del equipo no puede pasar de 100 caracteres");
		}
		if (dto.getCiudad() == null || dto.getCiudad().isBlank()) {
			throw new DatoInvalidoException("La ciudad del equipo es obligatoria");
		}
		if (dto.getCiudad().trim().length() > 60) {
			throw new DatoInvalidoException("La ciudad no puede pasar de 60 caracteres");
		}
		if (dto.getFechaFundacion() == null) {
			throw new DatoInvalidoException("La fecha de fundación es obligatoria");
		}
		if (dto.getFechaFundacion().isAfter(LocalDate.now())) {
			throw new DatoInvalidoException("La fecha de fundación no puede ser futura");
		}
		if (dto.getIdSede() == null || estadioRepository.buscarPorId(dto.getIdSede()) == null) {
			throw new DatoInvalidoException("El estadio sede no existe");
		}
		if (dto.getIdTecnico() == null || tecnicoRepository.buscarPorId(dto.getIdTecnico()) == null) {
			throw new DatoInvalidoException("El técnico no existe");
		}
	}

	/** Quita espacios, vacíos y repetidos. Debe quedar al menos un color. */
	private List<String> limpiarColores(List<String> colores) {
		if (colores == null || colores.isEmpty()) {
			throw new DatoInvalidoException("El equipo debe tener al menos un color");
		}
		Set<String> limpios = new LinkedHashSet<>();
		for (String c : colores) {
			if (c != null && !c.isBlank()) {
				String color = c.trim();
				if (color.length() > 30) {
					throw new DatoInvalidoException("Un color no puede pasar de 30 caracteres");
				}
				limpios.add(color);
			}
		}
		if (limpios.isEmpty()) {
			throw new DatoInvalidoException("El equipo debe tener al menos un color");
		}
		return new ArrayList<>(limpios);
	}

	// ---------------- operaciones ----------------

	public List<EquipoDTO> listar() {
		List<EquipoDTO> lista = new ArrayList<>();
		for (Equipo e : equipoRepository.listar()) {
			lista.add(aDTO(e));
		}
		return lista;
	}

	public List<EquipoDTO> listarPorTorneo(Long idTorneo) {
		List<EquipoDTO> lista = new ArrayList<>();
		for (Equipo e : equipoRepository.listarPorTorneo(idTorneo)) {
			lista.add(aDTO(e));
		}
		return lista;
	}

	public EquipoDTO buscarPorId(Long id) {
		Equipo e = equipoRepository.buscarPorId(id);
		if (e == null) {
			throw new RecursoNoEncontradoException("No existe el equipo con id " + id);
		}
		return aDTO(e);
	}

	public EquipoDTO crear(EquipoDTO dto) {
		validar(dto);
		List<String> colores = limpiarColores(dto.getColores());
		dto.setIdEquipo(equipoRepository.siguienteId());
		equipoRepository.insertar(aEntity(dto), colores);
		return buscarPorId(dto.getIdEquipo());
	}

	public EquipoDTO actualizar(Long id, EquipoDTO dto) {
		if (equipoRepository.buscarPorId(id) == null) {
			throw new RecursoNoEncontradoException("No existe el equipo con id " + id);
		}
		validar(dto);
		List<String> colores = limpiarColores(dto.getColores());
		dto.setIdEquipo(id);
		equipoRepository.actualizar(aEntity(dto), colores);
		return buscarPorId(id);
	}

	public void eliminar(Long id) {
		if (equipoRepository.eliminar(id) == 0) {
			throw new RecursoNoEncontradoException("No existe el equipo con id " + id);
		}
	}
}