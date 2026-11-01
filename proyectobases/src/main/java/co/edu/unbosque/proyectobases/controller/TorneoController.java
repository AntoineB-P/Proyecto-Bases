package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.TorneoDTO;
import co.edu.unbosque.proyectobases.service.TorneoService;

@RestController
@RequestMapping("/torneos")
public class TorneoController {

	@Autowired
	private TorneoService torneoService;

	@GetMapping
	public ResponseEntity<List<TorneoDTO>> listar() {
		return new ResponseEntity<>(torneoService.listar(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TorneoDTO> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(torneoService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TorneoDTO> crear(@RequestBody TorneoDTO dto) {
		return new ResponseEntity<>(torneoService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TorneoDTO> actualizar(@PathVariable("id") Long id, @RequestBody TorneoDTO dto) {
		return new ResponseEntity<>(torneoService.actualizar(id, dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		torneoService.eliminar(id);
		return new ResponseEntity<>("Torneo eliminado", HttpStatus.OK);
	}
}