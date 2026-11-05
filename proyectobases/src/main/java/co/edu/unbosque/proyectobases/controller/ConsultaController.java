package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.EstadisticaJugadorDTO;
import co.edu.unbosque.proyectobases.dto.HistorialEquipoDTO;
import co.edu.unbosque.proyectobases.dto.PosicionDTO;
import co.edu.unbosque.proyectobases.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;

	@GetMapping("/posiciones/torneo/{idTorneo}")
	public ResponseEntity<List<PosicionDTO>> tablaPosiciones(@PathVariable("idTorneo") Long idTorneo) {
		return new ResponseEntity<>(consultaService.tablaPosiciones(idTorneo), HttpStatus.OK);
	}

	@GetMapping("/historial/equipo/{idEquipo}/torneo/{idTorneo}")
	public ResponseEntity<List<HistorialEquipoDTO>> historialEquipo(@PathVariable("idEquipo") Long idEquipo,
			@PathVariable("idTorneo") Long idTorneo) {
		return new ResponseEntity<>(consultaService.historialEquipo(idEquipo, idTorneo), HttpStatus.OK);
	}

	@GetMapping("/estadisticas/torneo/{idTorneo}")
	public ResponseEntity<List<EstadisticaJugadorDTO>> estadisticasJugadores(
			@PathVariable("idTorneo") Long idTorneo) {
		return new ResponseEntity<>(consultaService.estadisticasJugadores(idTorneo), HttpStatus.OK);
	}
}