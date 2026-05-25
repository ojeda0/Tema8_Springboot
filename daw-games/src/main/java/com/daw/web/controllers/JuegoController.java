package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.JuegoEntity;
import com.daw.services.JuegoService;
import com.daw.services.exceptions.JuegoException;
import com.daw.services.exceptions.JuegoNotFoundException;

@RestController
@RequestMapping("/juegos")
public class JuegoController {

	@Autowired
	private JuegoService juegoService;
	
	@GetMapping
	public ResponseEntity<List<JuegoEntity>> findAll() {
		
		return ResponseEntity.ok(this.juegoService.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		
		try {
			
			return ResponseEntity.ok(this.juegoService.findById(id));
			
		} catch(JuegoNotFoundException jnf) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jnf.getMessage());
			
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody JuegoEntity juegoEntity) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(this.juegoService.create(juegoEntity));
		
		}
		catch(JuegoException je) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(je.getMessage());
			
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarJuego(@PathVariable long id, @RequestBody JuegoEntity juegoEntity) {
		
		try {
			
			return ResponseEntity.ok(this.juegoService.modificarJuego(id, juegoEntity));
			
		} catch (JuegoNotFoundException jnf) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jnf.getMessage());
			
		} catch (JuegoException jx) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jx.getMessage());
			
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarJuego(@PathVariable long id) {
		
		try {
			
			this.juegoService.borrarJuego(id);
			return ResponseEntity.noContent().build();
			
		} catch (JuegoNotFoundException jnf) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jnf.getMessage());
			
		}
		
	}
	
	@GetMapping("/genero")
	public ResponseEntity<?> findByGenero(@RequestParam(defaultValue = "") String genero) {
		
		return ResponseEntity.ok(this.juegoService.findByGenero(genero));
		
	}
	
	@GetMapping("/nombre")
	public ResponseEntity<?> findByNombre(@RequestParam(defaultValue = "") String nombre) {
		
		return ResponseEntity.ok(this.juegoService.findByNombre(nombre));
		
	}
	
	@GetMapping("/plataforma")
	public ResponseEntity<?> findByPlataforma(@RequestParam(defaultValue = "") String plataforma) {
		
		return ResponseEntity.ok(this.juegoService.findByPlataforma(plataforma));
		
	}
	
	@GetMapping("/expansiones")
	public ResponseEntity<?> findByExpansiones() {
		
		return ResponseEntity.ok(this.juegoService.findByExpansiones());
		
	}
	
	@GetMapping("/dlc")
	public ResponseEntity<?> findByDlc() {
		
		return ResponseEntity.ok(this.juegoService.findByDlc());
		
	}
	
	@GetMapping("/base")
	public ResponseEntity<?> findByBase() {
		
		return ResponseEntity.ok(this.juegoService.findByBase());
		
	}
	
	@GetMapping("/precio")
	public ResponseEntity<?> findByPrecio(@RequestParam(name = "min", defaultValue = "0.0") double precioMin, 
			@RequestParam(name = "max", defaultValue = "9999.99") double precioMax) {
		
		return ResponseEntity.ok(this.juegoService.findByPrecio(precioMin, precioMax));
		
	}
	
	@GetMapping("/exitos")
	public ResponseEntity<?> findTop5() {
		
		return ResponseEntity.ok(this.juegoService.findTop5());
		
	}
	
	@PutMapping("/{id}/completar")
	public ResponseEntity<?> alternarCompletado(@PathVariable long id) {
		
		try {
			
			return ResponseEntity.ok(this.juegoService.alternarCompletado(id));
			
		} catch (JuegoNotFoundException jnf) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jnf.getMessage());
			
		}
		
	}
	
	@PutMapping("/descuento")
	public ResponseEntity<?> aplicarDescuento(@RequestParam(defaultValue = "") String genero, 
			@RequestParam(name = "porcentaje", defaultValue = "0.0") double descuento) {
		
		return ResponseEntity.ok(this.juegoService.aplicarDescuento(genero, descuento));
		
	}
	
	
}
