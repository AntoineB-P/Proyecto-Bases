package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.ArbitrajeDTO;
import co.edu.unbosque.proyectobases.service.ArbitrajeService;

@RestController
@RequestMapping("/arbitrajes")
public class ArbitrajeController {

	@Autowired
	private ArbitrajeService arbitrajeService;

	@GetMapping("/partido/{idPartido}")
	public ResponseEntity<List<ArbitrajeDTO>> listarPorPartido(@PathVariable("idPartido") Long idPartido) {
		return new ResponseEntity<>(arbitrajeService.listarPorPartido(idPartido), HttpStatus.OK);
	}

	@GetMapping("/arbitro/{cedula}")
	public ResponseEntity<List<ArbitrajeDTO>> listarPorArbitro(@PathVariable("cedula") String cedula) {
		return new ResponseEntity<>(arbitrajeService.listarPorArbitro(cedula), HttpStatus.OK);
	}

	@GetMapping("/partido/{idPartido}/arbitro/{cedula}")
	public ResponseEntity<ArbitrajeDTO> buscar(@PathVariable("idPartido") Long idPartido,
			@PathVariable("cedula") String cedula) {
		return new ResponseEntity<>(arbitrajeService.buscar(idPartido, cedula), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ArbitrajeDTO> crear(@RequestBody ArbitrajeDTO dto) {
		return new ResponseEntity<>(arbitrajeService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/partido/{idPartido}/arbitro/{cedula}")
	public ResponseEntity<ArbitrajeDTO> actualizar(@PathVariable("idPartido") Long idPartido,
			@PathVariable("cedula") String cedula, @RequestBody ArbitrajeDTO dto) {
		return new ResponseEntity<>(arbitrajeService.actualizar(idPartido, cedula, dto), HttpStatus.OK);
	}

	@DeleteMapping("/partido/{idPartido}/arbitro/{cedula}")
	public ResponseEntity<String> eliminar(@PathVariable("idPartido") Long idPartido,
			@PathVariable("cedula") String cedula) {
		arbitrajeService.eliminar(idPartido, cedula);
		return new ResponseEntity<>("Árbitro retirado del partido", HttpStatus.OK);
	}
}