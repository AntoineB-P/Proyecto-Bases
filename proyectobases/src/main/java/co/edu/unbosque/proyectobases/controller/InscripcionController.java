package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.InscripcionDTO;
import co.edu.unbosque.proyectobases.service.InscripcionService;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

	@Autowired
	private InscripcionService inscripcionService;

	@GetMapping("/equipo/{idEquipo}/torneo/{idTorneo}")
	public ResponseEntity<List<InscripcionDTO>> listarPorEquipoYTorneo(@PathVariable("idEquipo") Long idEquipo,
			@PathVariable("idTorneo") Long idTorneo) {
		return new ResponseEntity<>(inscripcionService.listarPorEquipoYTorneo(idEquipo, idTorneo), HttpStatus.OK);
	}

	@GetMapping("/jugador/{idJugador}")
	public ResponseEntity<List<InscripcionDTO>> listarPorJugador(@PathVariable("idJugador") Long idJugador) {
		return new ResponseEntity<>(inscripcionService.listarPorJugador(idJugador), HttpStatus.OK);
	}

	@GetMapping("/equipo/{idEquipo}/torneo/{idTorneo}/jugador/{idJugador}")
	public ResponseEntity<InscripcionDTO> buscar(@PathVariable("idEquipo") Long idEquipo,
			@PathVariable("idTorneo") Long idTorneo, @PathVariable("idJugador") Long idJugador) {
		return new ResponseEntity<>(inscripcionService.buscar(idEquipo, idTorneo, idJugador), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InscripcionDTO> crear(@RequestBody InscripcionDTO dto) {
		return new ResponseEntity<>(inscripcionService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/equipo/{idEquipo}/torneo/{idTorneo}/jugador/{idJugador}")
	public ResponseEntity<InscripcionDTO> actualizar(@PathVariable("idEquipo") Long idEquipo,
			@PathVariable("idTorneo") Long idTorneo, @PathVariable("idJugador") Long idJugador,
			@RequestBody InscripcionDTO dto) {
		return new ResponseEntity<>(inscripcionService.actualizar(idEquipo, idTorneo, idJugador, dto),
				HttpStatus.OK);
	}

	@DeleteMapping("/equipo/{idEquipo}/torneo/{idTorneo}/jugador/{idJugador}")
	public ResponseEntity<String> eliminar(@PathVariable("idEquipo") Long idEquipo,
			@PathVariable("idTorneo") Long idTorneo, @PathVariable("idJugador") Long idJugador) {
		inscripcionService.eliminar(idEquipo, idTorneo, idJugador);
		return new ResponseEntity<>("Inscripción eliminada", HttpStatus.OK);
	}
}