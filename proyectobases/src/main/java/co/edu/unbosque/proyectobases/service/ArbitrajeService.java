package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.ArbitrajeDTO;
import co.edu.unbosque.proyectobases.entity.Arbitraje;
import co.edu.unbosque.proyectobases.entity.Arbitro;
import co.edu.unbosque.proyectobases.exception.ConflictoException;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.ArbitrajeRepository;
import co.edu.unbosque.proyectobases.repository.ArbitroRepository;
import co.edu.unbosque.proyectobases.repository.PartidoRepository;

@Service
public class ArbitrajeService {

	private static final String[] ROLES = { "Central", "Asistente", "Cuarto árbitro", "VAR" };

	private final ArbitrajeRepository arbitrajeRepository;
	private final ArbitroRepository arbitroRepository;
	private final PartidoRepository partidoRepository;

	public ArbitrajeService(ArbitrajeRepository arbitrajeRepository, ArbitroRepository arbitroRepository,
			PartidoRepository partidoRepository) {
		this.arbitrajeRepository = arbitrajeRepository;
		this.arbitroRepository = arbitroRepository;
		this.partidoRepository = partidoRepository;
	}

	private ArbitrajeDTO aDTO(Arbitraje a) {
		ArbitrajeDTO dto = new ArbitrajeDTO();
		dto.setCedulaArbitro(a.getCedula());
		dto.setIdPartido(a.getIdPartido());
		dto.setRol(a.getRol());
		Arbitro arbitro = arbitroRepository.buscarPorCedula(a.getCedula());
		dto.setNombreArbitro(arbitro == null ? null : arbitro.getNombres() + " " + arbitro.getApellidos());
		return dto;
	}

	private List<ArbitrajeDTO> aDTOs(List<Arbitraje> lista) {
		List<ArbitrajeDTO> dtos = new ArrayList<>();
		for (Arbitraje a : lista) {
			dtos.add(aDTO(a));
		}
		return dtos;
	}

	/** Un partido solo puede tener un árbitro Central. */
	private void validarCentral(Long idPartido, String cedula, String rol) {
		if (!"Central".equals(rol)) {
			return;
		}
		for (Arbitraje otro : arbitrajeRepository.listarPorPartido(idPartido)) {
			if ("Central".equals(otro.getRol()) && !otro.getCedula().equals(cedula)) {
				throw new ConflictoException("El partido ya tiene un árbitro central");
			}
		}
	}

	public List<ArbitrajeDTO> listarPorPartido(Long idPartido) {
		return aDTOs(arbitrajeRepository.listarPorPartido(idPartido));
	}

	public List<ArbitrajeDTO> listarPorArbitro(String cedula) {
		return aDTOs(arbitrajeRepository.listarPorArbitro(cedula));
	}

	public ArbitrajeDTO buscar(Long idPartido, String cedula) {
		Arbitraje a = arbitrajeRepository.buscar(idPartido, cedula);
		if (a == null) {
			throw new RecursoNoEncontradoException("Ese árbitro no dirige ese partido");
		}
		return aDTO(a);
	}

	public ArbitrajeDTO crear(ArbitrajeDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del arbitraje son obligatorios");
		}
		Validador.obligatorio(dto.getIdPartido(), "partido");
		String cedula = Validador.texto(dto.getCedulaArbitro(), "cédula del árbitro", 15);
		String rol = Validador.opcion(dto.getRol(), "rol", ROLES);

		if (partidoRepository.buscarPorId(dto.getIdPartido()) == null) {
			throw new DatoInvalidoException("El partido no existe");
		}
		if (arbitroRepository.buscarPorCedula(cedula) == null) {
			throw new DatoInvalidoException("El árbitro no existe");
		}
		if (arbitrajeRepository.buscar(dto.getIdPartido(), cedula) != null) {
			throw new ConflictoException("Ese árbitro ya está asignado a este partido");
		}
		validarCentral(dto.getIdPartido(), cedula, rol);

		Arbitraje a = new Arbitraje();
		a.setIdPartido(dto.getIdPartido());
		a.setCedula(cedula);
		a.setRol(rol);
		arbitrajeRepository.insertar(a);
		return buscar(dto.getIdPartido(), cedula);
	}

	/** Solo se puede cambiar el rol. */
	public ArbitrajeDTO actualizar(Long idPartido, String cedula, ArbitrajeDTO dto) {
		buscar(idPartido, cedula);
		if (dto == null) {
			throw new DatoInvalidoException("Los datos del arbitraje son obligatorios");
		}
		String rol = Validador.opcion(dto.getRol(), "rol", ROLES);
		validarCentral(idPartido, cedula, rol);

		Arbitraje a = new Arbitraje();
		a.setIdPartido(idPartido);
		a.setCedula(cedula);
		a.setRol(rol);
		arbitrajeRepository.actualizar(a);
		return buscar(idPartido, cedula);
	}

	public void eliminar(Long idPartido, String cedula) {
		if (arbitrajeRepository.eliminar(idPartido, cedula) == 0) {
			throw new RecursoNoEncontradoException("Ese árbitro no dirige ese partido");
		}
	}
}