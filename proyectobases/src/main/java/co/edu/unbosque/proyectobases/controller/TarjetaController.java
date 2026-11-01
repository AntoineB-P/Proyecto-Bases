package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.TarjetaDTO;
import co.edu.unbosque.proyectobases.service.TarjetaService;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

	@Autowired
	private TarjetaService tarjetaService;

	@GetMapping("/participacion/{idParticipacion}")
	public ResponseEntity<List<TarjetaDTO>> listarPorParticipacion(
			@PathVariable("idParticipacion") Long idParticipacion) {
		return new ResponseEntity<>(tarjetaService.listarPorParticipacion(idParticipacion), HttpStatus.OK);
	}

	@GetMapping("/partido/{idPartido}")
	public ResponseEntity<List<TarjetaDTO>> listarPorPartido(@PathVariable("idPartido") Long idPartido) {
		return new ResponseEntity<>(tarjetaService.listarPorPartido(idPartido), HttpStatus.OK);
	}

	@GetMapping("/participacion/{idParticipacion}/tarjeta/{idTarjeta}")
	public ResponseEntity<TarjetaDTO> buscar(@PathVariable("idParticipacion") Long idParticipacion,
			@PathVariable("idTarjeta") Long idTarjeta) {
		return new ResponseEntity<>(tarjetaService.buscar(idParticipacion, idTarjeta), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TarjetaDTO> crear(@RequestBody TarjetaDTO dto) {
		return new ResponseEntity<>(tarjetaService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/participacion/{idParticipacion}/tarjeta/{idTarjeta}")
	public ResponseEntity<TarjetaDTO> actualizar(@PathVariable("idParticipacion") Long idParticipacion,
			@PathVariable("idTarjeta") Long idTarjeta, @RequestBody TarjetaDTO dto) {
		return new ResponseEntity<>(tarjetaService.actualizar(idParticipacion, idTarjeta, dto), HttpStatus.OK);
	}

	@DeleteMapping("/participacion/{idParticipacion}/tarjeta/{idTarjeta}")
	public ResponseEntity<String> eliminar(@PathVariable("idParticipacion") Long idParticipacion,
			@PathVariable("idTarjeta") Long idTarjeta) {
		tarjetaService.eliminar(idParticipacion, idTarjeta);
		return new ResponseEntity<>("Tarjeta eliminada", HttpStatus.OK);
	}
}