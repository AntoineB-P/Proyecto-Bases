package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.EquipoDTO;
import co.edu.unbosque.proyectobases.service.EquipoService;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

	@Autowired
	private EquipoService equipoService;

	@GetMapping
	public ResponseEntity<List<EquipoDTO>> listar(
			@RequestParam(name = "idTorneo", required = false) Long idTorneo) {
		if (idTorneo != null) {
			return new ResponseEntity<>(equipoService.listarPorTorneo(idTorneo), HttpStatus.OK);
		}
		return new ResponseEntity<>(equipoService.listar(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EquipoDTO> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(equipoService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EquipoDTO> crear(@RequestBody EquipoDTO dto) {
		return new ResponseEntity<>(equipoService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EquipoDTO> actualizar(@PathVariable("id") Long id, @RequestBody EquipoDTO dto) {
		return new ResponseEntity<>(equipoService.actualizar(id, dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		equipoService.eliminar(id);
		return new ResponseEntity<>("Equipo eliminado", HttpStatus.OK);
	}
}