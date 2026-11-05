package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.ResultadoDTO;
import co.edu.unbosque.proyectobases.service.ResultadoService;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

	@Autowired
	private ResultadoService resultadoService;

	@GetMapping("/partido/{idPartido}")
	public ResponseEntity<ResultadoDTO> buscarPorPartido(@PathVariable("idPartido") Long idPartido) {
		return new ResponseEntity<>(resultadoService.buscarPorPartido(idPartido), HttpStatus.OK);
	}

	@GetMapping("/torneo/{idTorneo}")
	public ResponseEntity<List<ResultadoDTO>> listarPorTorneo(@PathVariable("idTorneo") Long idTorneo) {
		return new ResponseEntity<>(resultadoService.listarPorTorneo(idTorneo), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResultadoDTO> crear(@RequestBody ResultadoDTO dto) {
		return new ResponseEntity<>(resultadoService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/partido/{idPartido}")
	public ResponseEntity<ResultadoDTO> actualizar(@PathVariable("idPartido") Long idPartido,
			@RequestBody ResultadoDTO dto) {
		return new ResponseEntity<>(resultadoService.actualizar(idPartido, dto), HttpStatus.OK);
	}

	@DeleteMapping("/partido/{idPartido}")
	public ResponseEntity<String> eliminar(@PathVariable("idPartido") Long idPartido) {
		resultadoService.eliminar(idPartido);
		return new ResponseEntity<>("Resultado eliminado", HttpStatus.OK);
	}
}