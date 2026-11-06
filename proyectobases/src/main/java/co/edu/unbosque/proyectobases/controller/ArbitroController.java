package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.ArbitroDTO;
import co.edu.unbosque.proyectobases.service.ArbitroService;

@RestController
@RequestMapping("/arbitros")
public class ArbitroController {

	@Autowired
	private ArbitroService arbitroService;

	@GetMapping
	public ResponseEntity<List<ArbitroDTO>> listar() {
		return new ResponseEntity<>(arbitroService.listar(), HttpStatus.OK);
	}

	@GetMapping("/{cedula}")
	public ResponseEntity<ArbitroDTO> buscarPorCedula(@PathVariable("cedula") String cedula) {
		return new ResponseEntity<>(arbitroService.buscarPorCedula(cedula), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ArbitroDTO> crear(@RequestBody ArbitroDTO dto) {
		return new ResponseEntity<>(arbitroService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{cedula}")
	public ResponseEntity<ArbitroDTO> actualizar(@PathVariable("cedula") String cedula,
			@RequestBody ArbitroDTO dto) {
		return new ResponseEntity<>(arbitroService.actualizar(cedula, dto), HttpStatus.OK);
	}

	@DeleteMapping("/{cedula}")
	public ResponseEntity<String> eliminar(@PathVariable("cedula") String cedula) {
		arbitroService.eliminar(cedula);
		return new ResponseEntity<>("Árbitro eliminado", HttpStatus.OK);
	}
}