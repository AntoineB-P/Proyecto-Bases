package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.ParticipacionDTO;
import co.edu.unbosque.proyectobases.service.ParticipacionService;

@RestController
@RequestMapping("/participaciones")
public class ParticipacionController {

	@Autowired
	private ParticipacionService participacionService;

	@GetMapping("/{id}")
	public ResponseEntity<ParticipacionDTO> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(participacionService.buscarPorId(id), HttpStatus.OK);
	}

	@GetMapping("/partido/{idPartido}")
	public ResponseEntity<List<ParticipacionDTO>> listarPorPartido(@PathVariable("idPartido") Long idPartido) {
		return new ResponseEntity<>(participacionService.listarPorPartido(idPartido), HttpStatus.OK);
	}

	@GetMapping("/jugador/{idJugador}")
	public ResponseEntity<List<ParticipacionDTO>> listarPorJugador(@PathVariable("idJugador") Long idJugador) {
		return new ResponseEntity<>(participacionService.listarPorJugador(idJugador), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ParticipacionDTO> crear(@RequestBody ParticipacionDTO dto) {
		return new ResponseEntity<>(participacionService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParticipacionDTO> actualizar(@PathVariable("id") Long id,
			@RequestBody ParticipacionDTO dto) {
		return new ResponseEntity<>(participacionService.actualizar(id, dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		participacionService.eliminar(id);
		return new ResponseEntity<>("Participación eliminada", HttpStatus.OK);
	}
}