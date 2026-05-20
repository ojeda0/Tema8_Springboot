package com.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.JuegoEntity;
import com.daw.persistence.repositories.JuegoRepository;
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
	
}
