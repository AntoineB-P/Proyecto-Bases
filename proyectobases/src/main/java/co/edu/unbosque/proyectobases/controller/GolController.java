package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.GolDTO;
import co.edu.unbosque.proyectobases.service.GolService;

@RestController
@RequestMapping("/goles")
public class GolController {

	@Autowired
	private GolService golService;

	@GetMapping("/participacion/{idParticipacion}")
	public ResponseEntity<List<GolDTO>> listarPorParticipacion(
			@PathVariable("idParticipacion") Long idParticipacion) {
		return new ResponseEntity<>(golService.listarPorParticipacion(idParticipacion), HttpStatus.OK);
	}

	@GetMapping("/partido/{idPartido}")
	public ResponseEntity<List<GolDTO>> listarPorPartido(@PathVariable("idPartido") Long idPartido) {
		return new ResponseEntity<>(golService.listarPorPartido(idPartido), HttpStatus.OK);
	}

	@GetMapping("/participacion/{idParticipacion}/gol/{idGol}")
	public ResponseEntity<GolDTO> buscar(@PathVariable("idParticipacion") Long idParticipacion,
			@PathVariable("idGol") Long idGol) {
		return new ResponseEntity<>(golService.buscar(idParticipacion, idGol), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<GolDTO> crear(@RequestBody GolDTO dto) {
		return new ResponseEntity<>(golService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/participacion/{idParticipacion}/gol/{idGol}")
	public ResponseEntity<GolDTO> actualizar(@PathVariable("idParticipacion") Long idParticipacion,
			@PathVariable("idGol") Long idGol, @RequestBody GolDTO dto) {
		return new ResponseEntity<>(golService.actualizar(idParticipacion, idGol, dto), HttpStatus.OK);
	}

	@DeleteMapping("/participacion/{idParticipacion}/gol/{idGol}")
	public ResponseEntity<String> eliminar(@PathVariable("idParticipacion") Long idParticipacion,
			@PathVariable("idGol") Long idGol) {
		golService.eliminar(idParticipacion, idGol);
		return new ResponseEntity<>("Gol eliminado", HttpStatus.OK);
	}
}