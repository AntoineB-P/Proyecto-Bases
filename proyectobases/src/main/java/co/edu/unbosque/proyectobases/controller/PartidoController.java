package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.PartidoDTO;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.service.PartidoService;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

	@Autowired
	private PartidoService partidoService;

	@GetMapping
	public ResponseEntity<List<PartidoDTO>> listar(
			@RequestParam(name = "idTorneo", required = false) Long idTorneo,
			@RequestParam(name = "jornada", required = false) Integer jornada,
			@RequestParam(name = "idEquipo", required = false) Long idEquipo) {
		if (jornada != null && idTorneo == null) {
			throw new DatoInvalidoException("Para filtrar por jornada hay que enviar también idTorneo");
		}
		if (idTorneo != null && jornada != null) {
			return new ResponseEntity<>(partidoService.listarPorJornada(idTorneo, jornada), HttpStatus.OK);
		}
		if (idTorneo != null) {
			return new ResponseEntity<>(partidoService.listarPorTorneo(idTorneo), HttpStatus.OK);
		}
		if (idEquipo != null) {
			return new ResponseEntity<>(partidoService.listarPorEquipo(idEquipo), HttpStatus.OK);
		}
		return new ResponseEntity<>(partidoService.listar(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PartidoDTO> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(partidoService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PartidoDTO> crear(@RequestBody PartidoDTO dto) {
		return new ResponseEntity<>(partidoService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PartidoDTO> actualizar(@PathVariable("id") Long id, @RequestBody PartidoDTO dto) {
		return new ResponseEntity<>(partidoService.actualizar(id, dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		partidoService.eliminar(id);
		return new ResponseEntity<>("Partido eliminado", HttpStatus.OK);
	}
}