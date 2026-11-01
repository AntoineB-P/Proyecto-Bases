package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.EstadioDTO;
import co.edu.unbosque.proyectobases.service.EstadioService;

@RestController
@RequestMapping("/estadios")
public class EstadioController {

	@Autowired
	private EstadioService estadioService;

	@GetMapping
	public ResponseEntity<List<EstadioDTO>> listar() {
		return new ResponseEntity<>(estadioService.listar(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstadioDTO> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(estadioService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EstadioDTO> crear(@RequestBody EstadioDTO dto) {
		return new ResponseEntity<>(estadioService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EstadioDTO> actualizar(@PathVariable("id") Long id, @RequestBody EstadioDTO dto) {
		return new ResponseEntity<>(estadioService.actualizar(id, dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		estadioService.eliminar(id);
		return new ResponseEntity<>("Estadio eliminado", HttpStatus.OK);
	}
}