package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.EstadioDTO;
import co.edu.unbosque.proyectobases.entity.Estadio;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.EstadioRepository;

@Service
public class EstadioService {

	private final EstadioRepository estadioRepository;

	public EstadioService(EstadioRepository estadioRepository) {
		this.estadioRepository = estadioRepository;
	}

	private EstadioDTO aDTO(Estadio e) {
		EstadioDTO dto = new EstadioDTO();
		dto.setIdEstadio(e.getIdEstadio());
		dto.setNombre(e.getNombreEstadio());
		dto.setCapacidad(e.getCapacidad());
		dto.setDireccion(e.getDireccion());
		dto.setCiudad(e.getCiudadEstadio());
		dto.setBarrio(e.getBarrio());
		return dto;
	}

	/** Valida el DTO y lo convierte en entity. */
	private Estadio aEntity(EstadioDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del estadio son obligatorios");
		}
		if (dto.getCapacidad() <= 0) {
			throw new DatoInvalidoException("La capacidad debe ser mayor que 0");
		}
		Estadio e = new Estadio();
		e.setNombreEstadio(Validador.texto(dto.getNombre(), "nombre", 100));
		e.setCapacidad(dto.getCapacidad());
		e.setDireccion(Validador.texto(dto.getDireccion(), "dirección", 150));
		e.setCiudadEstadio(Validador.texto(dto.getCiudad(), "ciudad", 60));
		e.setBarrio(Validador.texto(dto.getBarrio(), "barrio", 60));
		return e;
	}

	public List<EstadioDTO> listar() {
		List<EstadioDTO> lista = new ArrayList<>();
		for (Estadio e : estadioRepository.listar()) {
			lista.add(aDTO(e));
		}
		return lista;
	}

	public EstadioDTO buscarPorId(Long id) {
		Estadio e = estadioRepository.buscarPorId(id);
		if (e == null) {
			throw new RecursoNoEncontradoException("No existe el estadio con id " + id);
		}
		return aDTO(e);
	}

	public EstadioDTO crear(EstadioDTO dto) {
		Estadio e = aEntity(dto);
		e.setIdEstadio(estadioRepository.siguienteId());
		estadioRepository.insertar(e);
		return buscarPorId(e.getIdEstadio());
	}

	public EstadioDTO actualizar(Long id, EstadioDTO dto) {
		buscarPorId(id); // lanza 404 si no existe
		Estadio e = aEntity(dto);
		e.setIdEstadio(id);
		estadioRepository.actualizar(e);
		return buscarPorId(id);
	}

	public void eliminar(Long id) {
		if (estadioRepository.eliminar(id) == 0) {
			throw new RecursoNoEncontradoException("No existe el estadio con id " + id);
		}
	}
}