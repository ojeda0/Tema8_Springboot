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
	
	//Modificar tarea por id
	public TareaEntity modificarTarea(long id, TareaEntity t) {
		
		if(t.getId() != id) {
			
			throw new TareaException(String.format("El id del body (%d) y el id del path (%d) no coinciden", t.getId(), id));
			
		}
		
		if(t.getEstado() != null) {
			
			throw new TareaException("ERROR. No se puede modificar el estado de una tarea.");
			
		}
		
		if(t.getFechaCreacion() != null) {
			
			throw new TareaException("ERROR. No se puede modificar la fecha de creación de una tarea.");
			
		}
		
		if(t.getFechaVencimiento().isBefore(LocalDate.now())) {
			
			throw new TareaException("ERROR. La fecha de vencimiento no puede ser anterior a la fecha actual.");
			
		}
		
		TareaEntity tarea = this.findById(id);
		tarea.setTitulo(t.getTitulo());
		tarea.setDescripcion(t.getDescripcion());
		tarea.setFechaVencimiento(t.getFechaVencimiento());
		
		return this.tareaRepository.save(tarea);
		
	}
	
	//Borrar tarea
	public void borrarTarea(long id) {
		
		if(!this.tareaRepository.existsById(id)) {
			
			throw new TareaNotFoundException("ERROR. La tarea no existe.");
			
		}
		
		this.tareaRepository.deleteById(id);
		
	}
	
	//Iniciar tarea
	public TareaEntity iniciarTarea(long id) {
		
		TareaEntity tarea = this.findById(id);
		
		if(!tarea.getEstado().equals(EstadoTarea.PENDIENTE)) {
			
			throw new TareaException("ERROR. No se puede iniciar una tarea que no esté pendiente.");
			
		}
		
		tarea.setEstado(EstadoTarea.EN_PROGRESO);
		
		return this.tareaRepository.save(tarea);
		
	}
	
}
