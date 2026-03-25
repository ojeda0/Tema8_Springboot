package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.TareaEntity;
import com.daw.persistence.entities.enums.EstadoTarea;
import com.daw.persistence.repositories.TareaRepository;
import com.daw.services.exceptions.TareaException;
import com.daw.services.exceptions.TareaNotFoundException;

@Service
public class TareaService {

	@Autowired
	private TareaRepository tareaRepository;
	
	//Ver todas las tareas
	public List<TareaEntity> findAll() {
		
		return this.tareaRepository.findAll();
		
	}
	
	//Ver tarea por ID
	public TareaEntity findById(long id) {
		
		if(!this.tareaRepository.existsById(id)) {
			
			throw new TareaNotFoundException(String.format("ERROR. No se encuentra la tarea con id: %d", id));
			
		}
		
		return this.tareaRepository.findById(id).get();
		
	}
	
	//Crear tarea
	public TareaEntity create(TareaEntity t) {
		
		if(t.getFechaCreacion() != null) {
			
			throw new TareaException("ERROR. No se puede modificar la fecha de creación de una tarea.");
			
		}
		
		if(t.getEstado() != null) {
			
			throw new TareaException("ERROR. No se puede moficiar el estado de una tarea.");
			
		}
		
		if(t.getFechaVencimiento().isBefore(LocalDate.now())) {
			
			throw new TareaException("ERROR. La fecha de vencimiento no puede ser anterior a la fecha actual.");
			
		}
		
		t.setId(0);
		t.setFechaCreacion(LocalDate.now());
		t.setEstado(EstadoTarea.PENDIENTE);
		
		return this.tareaRepository.save(t);
		
	}
	
	//Iniciar tarea por id
	public TareaEntity iniciarTarea(long id) {
		
		TareaEntity t = this.findById(id);
		
		if(!t.getEstado().equals(EstadoTarea.PENDIENTE)) {
			
			throw new TareaException("ERROR. No se puede iniciar una tarea que ya ha sido iniciada.");
			
		}
		
		t.setEstado(EstadoTarea.EN_PROGRESO);
		
		return this.tareaRepository.save(t);
		
	}
	
}
