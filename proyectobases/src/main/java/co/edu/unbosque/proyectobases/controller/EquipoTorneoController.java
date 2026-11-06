package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.EquipoTorneoDTO;
import co.edu.unbosque.proyectobases.service.EquipoTorneoService;

@RestController
@RequestMapping("/equipo-torneo")
public class EquipoTorneoController {

	@Autowired
	private EquipoTorneoService equipoTorneoService;

	@GetMapping("/torneo/{idTorneo}")
	public ResponseEntity<List<EquipoTorneoDTO>> listarPorTorneo(@PathVariable("idTorneo") Long idTorneo) {
		return new ResponseEntity<>(equipoTorneoService.listarPorTorneo(idTorneo), HttpStatus.OK);
	}

	@GetMapping("/equipo/{idEquipo}")
	public ResponseEntity<List<EquipoTorneoDTO>> listarPorEquipo(@PathVariable("idEquipo") Long idEquipo) {
		return new ResponseEntity<>(equipoTorneoService.listarPorEquipo(idEquipo), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EquipoTorneoDTO> crear(@RequestBody EquipoTorneoDTO dto) {
		return new ResponseEntity<>(equipoTorneoService.crear(dto), HttpStatus.CREATED);
	}

	@DeleteMapping("/equipo/{idEquipo}/torneo/{idTorneo}")
	public ResponseEntity<String> eliminar(@PathVariable("idEquipo") Long idEquipo,
			@PathVariable("idTorneo") Long idTorneo) {
		equipoTorneoService.eliminar(idEquipo, idTorneo);
		return new ResponseEntity<>("Equipo retirado del torneo", HttpStatus.OK);
	}
}