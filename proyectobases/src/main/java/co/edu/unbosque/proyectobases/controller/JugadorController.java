package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.JugadorDTO;
import co.edu.unbosque.proyectobases.exception.DatoInvalidoException;
import co.edu.unbosque.proyectobases.service.JugadorService;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

	@Autowired
	private JugadorService jugadorService;

	@GetMapping
	public ResponseEntity<List<JugadorDTO>> listar(
			@RequestParam(name = "idEquipo", required = false) Long idEquipo,
			@RequestParam(name = "idTorneo", required = false) Long idTorneo,
			@RequestParam(name = "posicion", required = false) String posicion) {
		if (idEquipo != null && idTorneo != null) {
			return new ResponseEntity<>(jugadorService.listarPorEquipoYTorneo(idEquipo, idTorneo), HttpStatus.OK);
		}
		if (idEquipo != null || idTorneo != null) {
			throw new DatoInvalidoException("Para filtrar la plantilla hay que enviar idEquipo e idTorneo juntos");
		}
		if (posicion != null) {
			return new ResponseEntity<>(jugadorService.listarPorPosicion(posicion), HttpStatus.OK);
		}
		return new ResponseEntity<>(jugadorService.listar(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JugadorDTO> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(jugadorService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<JugadorDTO> crear(@RequestBody JugadorDTO dto) {
		return new ResponseEntity<>(jugadorService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<JugadorDTO> actualizar(@PathVariable("id") Long id, @RequestBody JugadorDTO dto) {
		return new ResponseEntity<>(jugadorService.actualizar(id, dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		jugadorService.eliminar(id);
		return new ResponseEntity<>("Jugador eliminado", HttpStatus.OK);
	}
}