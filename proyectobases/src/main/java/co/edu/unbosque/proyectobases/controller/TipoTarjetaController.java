package co.edu.unbosque.proyectobases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.proyectobases.dto.TipoTarjetaDTO;
import co.edu.unbosque.proyectobases.service.TipoTarjetaService;

@RestController
@RequestMapping("/tipos-tarjeta")
public class TipoTarjetaController {

	@Autowired
	private TipoTarjetaService tipoTarjetaService;

	@GetMapping
	public ResponseEntity<List<TipoTarjetaDTO>> listar() {
		return new ResponseEntity<>(tipoTarjetaService.listar(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoTarjetaDTO> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(tipoTarjetaService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TipoTarjetaDTO> crear(@RequestBody TipoTarjetaDTO dto) {
		return new ResponseEntity<>(tipoTarjetaService.crear(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoTarjetaDTO> actualizar(@PathVariable("id") Long id,
			@RequestBody TipoTarjetaDTO dto) {
		return new ResponseEntity<>(tipoTarjetaService.actualizar(id, dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id) {
		tipoTarjetaService.eliminar(id);
		return new ResponseEntity<>("Tipo de tarjeta eliminado", HttpStatus.OK);
	}
}