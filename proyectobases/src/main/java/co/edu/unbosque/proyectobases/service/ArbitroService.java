package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import co.edu.unbosque.proyectobases.dto.ArbitroDTO;
import co.edu.unbosque.proyectobases.entity.Arbitro;
import co.edu.unbosque.proyectobases.exception.ConflictoException;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.ArbitrajeRepository;
import co.edu.unbosque.proyectobases.repository.ArbitroRepository;

@Service
public class ArbitroService {

	private final ArbitroRepository arbitroRepository;
	private final ArbitrajeRepository arbitrajeRepository;

	public ArbitroService(ArbitroRepository arbitroRepository, ArbitrajeRepository arbitrajeRepository) {
		this.arbitroRepository = arbitroRepository;
		this.arbitrajeRepository = arbitrajeRepository;
	}

	private ArbitroDTO aDTO(Arbitro a) {
		ArbitroDTO dto = new ArbitroDTO();
		dto.setCedula(a.getCedula());
		dto.setNombres(a.getNombres());
		dto.setApellidos(a.getApellidos());
		dto.setCiudad(a.getCiudadArbitro());
		dto.setCategoria(a.getCategoria());
		dto.setPartidosDirigidos(arbitrajeRepository.listarPorArbitro(a.getCedula()).size());
		return dto;
	}

	private Arbitro aEntity(ArbitroDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del árbitro son obligatorios");
		}
		Arbitro a = new Arbitro();
		a.setCedula(Validador.soloNumeros(dto.getCedula(), "cédula", 6, 15));
		a.setNombres(Validador.soloLetras(dto.getNombres(), "nombres", 80));
		a.setApellidos(Validador.soloLetras(dto.getApellidos(), "apellidos", 80));
		a.setCiudadArbitro(Validador.soloLetras(dto.getCiudad(), "ciudad", 60));
		a.setCategoria(Validador.opcion(dto.getCategoria(), "categoría", "Nacional", "FIFA"));
		return a;
	}

	public List<ArbitroDTO> listar() {
		List<ArbitroDTO> lista = new ArrayList<>();
		for (Arbitro a : arbitroRepository.listar()) {
			lista.add(aDTO(a));
		}
		return lista;
	}

	public ArbitroDTO buscarPorCedula(String cedula) {
		Arbitro a = arbitroRepository.buscarPorCedula(cedula);
		if (a == null) {
			throw new RecursoNoEncontradoException("No existe el árbitro con cédula " + cedula);
		}
		return aDTO(a);
	}

	public ArbitroDTO crear(ArbitroDTO dto) {
		Arbitro a = aEntity(dto);
		if (arbitroRepository.buscarPorCedula(a.getCedula()) != null) {
			throw new ConflictoException("Ya existe un árbitro con la cédula " + a.getCedula());
		}
		arbitroRepository.insertar(a);
		return buscarPorCedula(a.getCedula());
	}

	public ArbitroDTO actualizar(String cedula, ArbitroDTO dto) {
		buscarPorCedula(cedula);
		if (dto != null) {
			dto.setCedula(cedula);
		}
		Arbitro a = aEntity(dto);
		arbitroRepository.actualizar(a);
		return buscarPorCedula(cedula);
	}

	public void eliminar(String cedula) {
		if (arbitroRepository.eliminar(cedula) == 0) {
			throw new RecursoNoEncontradoException("No existe el árbitro con cédula " + cedula);
		}
	}
}