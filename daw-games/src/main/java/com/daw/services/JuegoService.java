package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.JuegoEntity;
import com.daw.persistence.repositories.JuegoRepository;
import com.daw.services.exceptions.JuegoException;
import com.daw.services.exceptions.JuegoNotFoundException;

@Service
public class JuegoService {

	@Autowired
	private JuegoRepository juegoRepository;
	
	//Obtener todos los juegos
	public List<JuegoEntity> findAll() {
		
		return this.juegoRepository.findAll();
		
	}
	
	//Obtener un juego mediante su ID
	public JuegoEntity findById(long id) {
		
		if(!this.juegoRepository.existsById(id)) {
			
			throw new JuegoNotFoundException(String.format("ERROR. No se encuentra el juego con id: %d", id));
			
		}
		
		return this.juegoRepository.findById(id).get();
		
	}
	
	//Crear un juego
	public JuegoEntity create(JuegoEntity j) {
			
		if(j.getFechaLanzamiento() == null) {
			
			j.setFechaLanzamiento(LocalDate.now());
			
		}
		
		j.setId(0);
		
		return this.juegoRepository.save(j);
			
	}
	
	//Modificar un juego con su id
	public JuegoEntity modificarJuego(long id, JuegoEntity j) {
		
		JuegoEntity juego = this.findById(id);
		
		if(j.getId() != id) {
			
			throw new JuegoException(String.format("El id del body (%d) y el id del path (%d) no coinciden", j.getId(), id));
			
		}
		
		if(j.getCompletado() != null) {
			
			throw new JuegoException("No se permite modificar el atributo completado");
			
		}
		
		return this.juegoRepository.save(juego);
		
	}
	
	
	
	
}
