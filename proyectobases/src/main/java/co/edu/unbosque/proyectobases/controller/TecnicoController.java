package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.TecnicoDTO;
import co.edu.unbosque.proyectobases.service.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> listar() {
		return new ResponseEntity<>(tecnicoService.listar(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TecnicoDTO> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(tecnicoService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TecnicoDTO> crear(@RequestBody TecnicoDTO dto) {
		return new ResponseEntity<>(tecnicoService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TecnicoDTO> actualizar(@PathVariable("id") Long id, @RequestBody TecnicoDTO dto) {
		return new ResponseEntity<>(tecnicoService.actualizar(id, dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		tecnicoService.eliminar(id);
		return new ResponseEntity<>("Técnico eliminado", HttpStatus.OK);
	}
}