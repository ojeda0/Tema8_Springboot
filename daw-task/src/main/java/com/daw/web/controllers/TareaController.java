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
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.TareaEntity;
import com.daw.services.TareaService;
import com.daw.services.exceptions.TareaException;
import com.daw.services.exceptions.TareaNotFoundException;

@RestController
@RequestMapping("/tareas")
public class TareaController {

	@Autowired
	private TareaService tareaService;
	
	@GetMapping
	public ResponseEntity<List<TareaEntity>> findAll() {
		
		return ResponseEntity.ok(this.tareaService.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		
		try {
			
			return ResponseEntity.ok(this.tareaService.findById(id));
		
		}
		catch(TareaNotFoundException tnf) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tnf.getMessage());
			
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody TareaEntity tareaEntity) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(this.tareaService.create(tareaEntity));
		
		}
		catch(TareaException te) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(te.getMessage());
			
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarTarea(@PathVariable long id, @RequestBody TareaEntity tareaEntity) {
		
		try {
			
			return ResponseEntity.ok(this.tareaService.modificarTarea(id, tareaEntity));
			
		} catch (TareaNotFoundException ex) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
			
		} catch (TareaException ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarTarea(@PathVariable long id) {
		
		try {
			
			this.tareaService.borrarTarea(id);
			return ResponseEntity.ok().build();
			
		} catch (TareaNotFoundException ex) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
			
		}
		
	}
	
	@PutMapping("/{id}/iniciar")
	public ResponseEntity<?> iniciarTarea(@PathVariable long id) {
		
		try {
			
			return ResponseEntity.ok(this.tareaService.iniciarTarea(id));
			
		} catch (TareaNotFoundException ex) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
			
		} catch (TareaException ex) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
		
	}
	
}
