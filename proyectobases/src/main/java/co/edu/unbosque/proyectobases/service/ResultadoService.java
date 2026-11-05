package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.ResultadoDTO;
import co.edu.unbosque.proyectobases.entity.Resultado;
import co.edu.unbosque.proyectobases.exception.ConflictoException;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.PartidoRepository;
import co.edu.unbosque.proyectobases.repository.ResultadoRepository;

@Service
public class ResultadoService {

	private final ResultadoRepository resultadoRepository;
	private final PartidoRepository partidoRepository;

	public ResultadoService(ResultadoRepository resultadoRepository, PartidoRepository partidoRepository) {
		this.resultadoRepository = resultadoRepository;
		this.partidoRepository = partidoRepository;
	}

	private ResultadoDTO aDTO(Resultado r) {
		ResultadoDTO dto = new ResultadoDTO();
		dto.setIdResultado(r.getIdResultado());
		dto.setIdPartido(r.getIdPartido());
		dto.setGolesLocal(r.getGolesLocal());
		dto.setGolesVisitante(r.getGolesVisitante());
		return dto;
	}

	private void validarGoles(ResultadoDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del resultado son obligatorios");
		}
		Validador.obligatorio(dto.getGolesLocal(), "goles del local");
		Validador.obligatorio(dto.getGolesVisitante(), "goles del visitante");
		if (dto.getGolesLocal() < 0 || dto.getGolesVisitante() < 0) {
			throw new DatoInvalidoException("Los goles no pueden ser negativos");
		}
	}

	public ResultadoDTO buscarPorPartido(Long idPartido) {
		Resultado r = resultadoRepository.buscarPorPartido(idPartido);
		if (r == null) {
			throw new RecursoNoEncontradoException("El partido " + idPartido + " todavía no tiene resultado");
		}
		return aDTO(r);
	}

	public List<ResultadoDTO> listarPorTorneo(Long idTorneo) {
		List<ResultadoDTO> lista = new ArrayList<>();
		for (Resultado r : resultadoRepository.listarPorTorneo(idTorneo)) {
			lista.add(aDTO(r));
		}
		return lista;
	}

	public ResultadoDTO crear(ResultadoDTO dto) {
		validarGoles(dto);
		Validador.obligatorio(dto.getIdPartido(), "partido");
		if (partidoRepository.buscarPorId(dto.getIdPartido()) == null) {
			throw new DatoInvalidoException("El partido no existe");
		}
		if (resultadoRepository.buscarPorPartido(dto.getIdPartido()) != null) {
			throw new ConflictoException("El partido ya tiene un resultado registrado");
		}
		Resultado r = new Resultado();
		r.setIdPartido(dto.getIdPartido());
		r.setIdResultado(resultadoRepository.siguienteId(dto.getIdPartido()));
		r.setGolesLocal(dto.getGolesLocal());
		r.setGolesVisitante(dto.getGolesVisitante());
		resultadoRepository.insertar(r);
		return buscarPorPartido(dto.getIdPartido());
	}

	public ResultadoDTO actualizar(Long idPartido, ResultadoDTO dto) {
		buscarPorPartido(idPartido);
		validarGoles(dto);
		Resultado r = new Resultado();
		r.setIdPartido(idPartido);
		r.setGolesLocal(dto.getGolesLocal());
		r.setGolesVisitante(dto.getGolesVisitante());
		resultadoRepository.actualizar(r);
		return buscarPorPartido(idPartido);
	}

	public void eliminar(Long idPartido) {
		if (resultadoRepository.eliminarPorPartido(idPartido) == 0) {
			throw new RecursoNoEncontradoException("El partido " + idPartido + " no tiene resultado");
		}
	}
}