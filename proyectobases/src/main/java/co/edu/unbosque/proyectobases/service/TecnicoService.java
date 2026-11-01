package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.TecnicoDTO;
import co.edu.unbosque.proyectobases.entity.Tecnico;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.TecnicoRepository;

@Service
public class TecnicoService {

	private final TecnicoRepository tecnicoRepository;

	public TecnicoService(TecnicoRepository tecnicoRepository) {
		this.tecnicoRepository = tecnicoRepository;
	}

	private TecnicoDTO aDTO(Tecnico t) {
		TecnicoDTO dto = new TecnicoDTO();
		dto.setIdTecnico(t.getIdTecnico());
		dto.setNombres(t.getNombres());
		dto.setApellidos(t.getApellidos());
		dto.setNacionalidad(t.getNacionalidad());
		dto.setFechaNacimiento(t.getFechaNacimiento());
		return dto;
	}

	private Tecnico aEntity(TecnicoDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del técnico son obligatorios");
		}
		Tecnico t = new Tecnico();
		t.setNombres(Validador.texto(dto.getNombres(), "nombres", 80));
		t.setApellidos(Validador.texto(dto.getApellidos(), "apellidos", 80));
		t.setNacionalidad(Validador.texto(dto.getNacionalidad(), "nacionalidad", 50));
		Validador.fechaNoFutura(dto.getFechaNacimiento(), "fecha de nacimiento");
		t.setFechaNacimiento(dto.getFechaNacimiento());
		return t;
	}

	public List<TecnicoDTO> listar() {
		List<TecnicoDTO> lista = new ArrayList<>();
		for (Tecnico t : tecnicoRepository.listar()) {
			lista.add(aDTO(t));
		}
		return lista;
	}

	public TecnicoDTO buscarPorId(Long id) {
		Tecnico t = tecnicoRepository.buscarPorId(id);
		if (t == null) {
			throw new RecursoNoEncontradoException("No existe el técnico con id " + id);
		}
		return aDTO(t);
	}

	public TecnicoDTO crear(TecnicoDTO dto) {
		Tecnico t = aEntity(dto);
		t.setIdTecnico(tecnicoRepository.siguienteId());
		tecnicoRepository.insertar(t);
		return buscarPorId(t.getIdTecnico());
	}

	public TecnicoDTO actualizar(Long id, TecnicoDTO dto) {
		buscarPorId(id);
		Tecnico t = aEntity(dto);
		t.setIdTecnico(id);
		tecnicoRepository.actualizar(t);
		return buscarPorId(id);
	}

	public void eliminar(Long id) {
		if (tecnicoRepository.eliminar(id) == 0) {
			throw new RecursoNoEncontradoException("No existe el técnico con id " + id);
		}
	}
}