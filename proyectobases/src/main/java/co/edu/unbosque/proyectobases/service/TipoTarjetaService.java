package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.TipoTarjetaDTO;
import co.edu.unbosque.proyectobases.entity.TipoTarjeta;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.TipoTarjetaRepository;

@Service
public class TipoTarjetaService {

	private final TipoTarjetaRepository tipoTarjetaRepository;

	public TipoTarjetaService(TipoTarjetaRepository tipoTarjetaRepository) {
		this.tipoTarjetaRepository = tipoTarjetaRepository;
	}

	private TipoTarjetaDTO aDTO(TipoTarjeta t) {
		TipoTarjetaDTO dto = new TipoTarjetaDTO();
		dto.setIdTipo(t.getIdTipo());
		dto.setColor(t.getColorTarjeta());
		return dto;
	}

	private String validarColor(TipoTarjetaDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del tipo de tarjeta son obligatorios");
		}
		return Validador.opcion(dto.getColor(), "color", "Amarilla", "Roja");
	}

	public List<TipoTarjetaDTO> listar() {
		List<TipoTarjetaDTO> lista = new ArrayList<>();
		for (TipoTarjeta t : tipoTarjetaRepository.listar()) {
			lista.add(aDTO(t));
		}
		return lista;
	}

	public TipoTarjetaDTO buscarPorId(Long id) {
		TipoTarjeta t = tipoTarjetaRepository.buscarPorId(id);
		if (t == null) {
			throw new RecursoNoEncontradoException("No existe el tipo de tarjeta con id " + id);
		}
		return aDTO(t);
	}

	public TipoTarjetaDTO crear(TipoTarjetaDTO dto) {
		TipoTarjeta t = new TipoTarjeta();
		t.setColorTarjeta(validarColor(dto));
		t.setIdTipo(tipoTarjetaRepository.siguienteId());
		tipoTarjetaRepository.insertar(t); // el color es UNIQUE: repetirlo da ConflictoException
		return buscarPorId(t.getIdTipo());
	}

	public TipoTarjetaDTO actualizar(Long id, TipoTarjetaDTO dto) {
		buscarPorId(id);
		TipoTarjeta t = new TipoTarjeta();
		t.setIdTipo(id);
		t.setColorTarjeta(validarColor(dto));
		tipoTarjetaRepository.actualizar(t);
		return buscarPorId(id);
	}

	public void eliminar(Long id) {
		if (tipoTarjetaRepository.eliminar(id) == 0) {
			throw new RecursoNoEncontradoException("No existe el tipo de tarjeta con id " + id);
		}
	}
}