package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.GolDTO;
import co.edu.unbosque.proyectobases.entity.Gol;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.GolRepository;
import co.edu.unbosque.proyectobases.repository.ParticipacionRepository;

@Service
public class GolService {

	private static final String[] TIPOS = { "Jugada", "Penal", "Tiro libre", "Cabeza" };

	private final GolRepository golRepository;
	private final ParticipacionRepository participacionRepository;

	public GolService(GolRepository golRepository, ParticipacionRepository participacionRepository) {
		this.golRepository = golRepository;
		this.participacionRepository = participacionRepository;
	}

	private GolDTO aDTO(Gol g) {
		GolDTO dto = new GolDTO();
		dto.setIdGol(g.getIdGol());
		dto.setIdParticipacion(g.getIdParticipacion());
		dto.setTipo(g.getTipoGol());
		dto.setMinuto(g.getMinutoGol());
		return dto;
	}

	private List<GolDTO> aDTOs(List<Gol> lista) {
		List<GolDTO> dtos = new ArrayList<>();
		for (Gol g : lista) {
			dtos.add(aDTO(g));
		}
		return dtos;
	}

	public List<GolDTO> listarPorParticipacion(Long idParticipacion) {
		return aDTOs(golRepository.listarPorParticipacion(idParticipacion));
	}

	public List<GolDTO> listarPorPartido(Long idPartido) {
		return aDTOs(golRepository.listarPorPartido(idPartido));
	}

	public GolDTO buscar(Long idParticipacion, Long idGol) {
		Gol g = golRepository.buscar(idParticipacion, idGol);
		if (g == null) {
			throw new RecursoNoEncontradoException("No existe ese gol");
		}
		return aDTO(g);
	}

	public GolDTO crear(GolDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del gol son obligatorios");
		}
		Validador.obligatorio(dto.getIdParticipacion(), "participación");
		String tipo = Validador.opcion(dto.getTipo(), "tipo de gol", TIPOS);
		Validador.rango(dto.getMinuto(), "minuto", 1, 120);
		if (participacionRepository.buscarPorId(dto.getIdParticipacion()) == null) {
			throw new DatoInvalidoException("La participación no existe");
		}
		Gol g = new Gol();
		g.setIdParticipacion(dto.getIdParticipacion());
		g.setIdGol(golRepository.siguienteId(dto.getIdParticipacion()));
		g.setTipoGol(tipo);
		g.setMinutoGol(dto.getMinuto());
		golRepository.insertar(g);
		return buscar(g.getIdParticipacion(), g.getIdGol());
	}

	public GolDTO actualizar(Long idParticipacion, Long idGol, GolDTO dto) {
		buscar(idParticipacion, idGol);
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del gol son obligatorios");
		}
		Gol g = new Gol();
		g.setIdParticipacion(idParticipacion);
		g.setIdGol(idGol);
		g.setTipoGol(Validador.opcion(dto.getTipo(), "tipo de gol", TIPOS));
		Validador.rango(dto.getMinuto(), "minuto", 1, 120);
		g.setMinutoGol(dto.getMinuto());
		golRepository.actualizar(g);
		return buscar(idParticipacion, idGol);
	}

	public void eliminar(Long idParticipacion, Long idGol) {
		if (golRepository.eliminar(idParticipacion, idGol) == 0) {
			throw new RecursoNoEncontradoException("No existe ese gol");
		}
	}
}