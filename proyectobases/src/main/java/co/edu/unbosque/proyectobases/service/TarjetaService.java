package co.edu.unbosque.proyectobases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.dto.TarjetaDTO;
import co.edu.unbosque.proyectobases.entity.Tarjeta;
import co.edu.unbosque.proyectobases.entity.TipoTarjeta;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.exception.RecursoNoEncontradoException;
import co.edu.unbosque.proyectobases.repository.ParticipacionRepository;
import co.edu.unbosque.proyectobases.repository.TarjetaRepository;
import co.edu.unbosque.proyectobases.repository.TipoTarjetaRepository;

@Service
public class TarjetaService {

	private final TarjetaRepository tarjetaRepository;
	private final TipoTarjetaRepository tipoTarjetaRepository;
	private final ParticipacionRepository participacionRepository;

	public TarjetaService(TarjetaRepository tarjetaRepository, TipoTarjetaRepository tipoTarjetaRepository,
			ParticipacionRepository participacionRepository) {
		this.tarjetaRepository = tarjetaRepository;
		this.tipoTarjetaRepository = tipoTarjetaRepository;
		this.participacionRepository = participacionRepository;
	}

	private TarjetaDTO aDTO(Tarjeta t) {
		TarjetaDTO dto = new TarjetaDTO();
		dto.setIdTarjeta(t.getIdTarjeta());
		dto.setIdParticipacion(t.getIdParticipacion());
		dto.setIdTipo(t.getIdTipo());
		dto.setMotivo(t.getMotivo());
		dto.setMinuto(t.getMinutoTarjeta());
		TipoTarjeta tipo = tipoTarjetaRepository.buscarPorId(t.getIdTipo());
		dto.setColor(tipo == null ? null : tipo.getColorTarjeta());
		return dto;
	}

	private List<TarjetaDTO> aDTOs(List<Tarjeta> lista) {
		List<TarjetaDTO> dtos = new ArrayList<>();
		for (Tarjeta t : lista) {
			dtos.add(aDTO(t));
		}
		return dtos;
	}

	/** Valida tipo, motivo y minuto, y los copia en la entity. */
	private void llenarCampos(Tarjeta t, TarjetaDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos de la tarjeta son obligatorios");
		}
		Validador.obligatorio(dto.getIdTipo(), "tipo de tarjeta");
		if (tipoTarjetaRepository.buscarPorId(dto.getIdTipo()) == null) {
			throw new DatoInvalidoException("El tipo de tarjeta no existe");
		}
		t.setIdTipo(dto.getIdTipo());
		t.setMotivo(Validador.texto(dto.getMotivo(), "motivo", 150));
		Validador.rango(dto.getMinuto(), "minuto", 1, 120);
		t.setMinutoTarjeta(dto.getMinuto());
	}

	public List<TarjetaDTO> listarPorParticipacion(Long idParticipacion) {
		return aDTOs(tarjetaRepository.listarPorParticipacion(idParticipacion));
	}

	public List<TarjetaDTO> listarPorPartido(Long idPartido) {
		return aDTOs(tarjetaRepository.listarPorPartido(idPartido));
	}

	public TarjetaDTO buscar(Long idParticipacion, Long idTarjeta) {
		Tarjeta t = tarjetaRepository.buscar(idParticipacion, idTarjeta);
		if (t == null) {
			throw new RecursoNoEncontradoException("No existe esa tarjeta");
		}
		return aDTO(t);
	}

	public TarjetaDTO crear(TarjetaDTO dto) {
		if (dto == null) {
			throw new DatoInvalidoException("Los datos de la tarjeta son obligatorios");
		}
		Validador.obligatorio(dto.getIdParticipacion(), "participación");
		if (participacionRepository.buscarPorId(dto.getIdParticipacion()) == null) {
			throw new DatoInvalidoException("La participación no existe");
		}
		Tarjeta t = new Tarjeta();
		llenarCampos(t, dto);
		t.setIdParticipacion(dto.getIdParticipacion());
		t.setIdTarjeta(tarjetaRepository.siguienteId(dto.getIdParticipacion()));
		tarjetaRepository.insertar(t);
		return buscar(t.getIdParticipacion(), t.getIdTarjeta());
	}

	public TarjetaDTO actualizar(Long idParticipacion, Long idTarjeta, TarjetaDTO dto) {
		buscar(idParticipacion, idTarjeta);
		Tarjeta t = new Tarjeta();
		llenarCampos(t, dto);
		t.setIdParticipacion(idParticipacion);
		t.setIdTarjeta(idTarjeta);
		tarjetaRepository.actualizar(t);
		return buscar(idParticipacion, idTarjeta);
	}

	public void eliminar(Long idParticipacion, Long idTarjeta) {
		if (tarjetaRepository.eliminar(idParticipacion, idTarjeta) == 0) {
			throw new RecursoNoEncontradoException("No existe esa tarjeta");
		}
	}
}