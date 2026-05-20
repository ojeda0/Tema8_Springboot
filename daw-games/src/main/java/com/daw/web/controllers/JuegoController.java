package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.daw.persistence.entities.JuegoEntity;
import com.daw.services.JuegoService;
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
	
}
