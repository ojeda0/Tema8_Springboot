package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.JuegoEntity;
import com.daw.persistence.entities.enums.TipoJuego;
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
		
		if(j.getCompletado() != null && j.getCompletado()) {
			
			throw new JuegoException("ERROR. Un juego debe ser creado en estado incompleto (completo=false). Usa el endpoint para completarlo más tarde");
			
		}
		
		if(j.getFechaLanzamiento() == null) {
			
			j.setFechaLanzamiento(LocalDate.now());
			
		}
		
		j.setId(0);
		j.setCompletado(false);
		
		return this.juegoRepository.save(j);
			
	}
	
	//Modificar un juego con su id
	public JuegoEntity modificarJuego(long id, JuegoEntity j) {
		
		JuegoEntity juego = this.findById(id);
		
		if(j.getId() != id) {
			
			throw new JuegoException(String.format("ERROR. El id del body (%d) y el id del path (%d) no coinciden", j.getId(), id));
			
		}
		
		if(j.getCompletado() != null) {
			
			throw new JuegoException("ERROR. No se permite modificar el atributo completado");
			
		}
		
		juego.setNombre(j.getNombre());
		juego.setGenero(j.getGenero());
		juego.setPlataformas(j.getPlataformas());
		juego.setPrecio(j.getPrecio());
		juego.setDescargas(j.getDescargas());
		juego.setFechaLanzamiento(j.getFechaLanzamiento());
		juego.setTipo(j.getTipo());
		
		return this.juegoRepository.save(juego);
		
	}
	
	//Borrar un juego
	public void borrarJuego(long id) {
		
		if(!this.juegoRepository.existsById(id)) {
			
			throw new JuegoNotFoundException(String.format("ERROR. No se encuentra el juego con id: %d", id));
			
		}
		
		this.juegoRepository.deleteById(id);
		
	}
	
	//Buscar juegos por género
	public List<JuegoEntity> findByGenero(String genero) {
		
		return this.juegoRepository.findByGeneroContainingIgnoreCase(genero);
		
	}
	
	//Buscar juegos por nombre
	public List<JuegoEntity> findByNombre(String nombre) {
		
		return this.juegoRepository.findByNombreContainingIgnoreCase(nombre);
		
	}
	
	//Buscar juegos por plataforma
	public List<JuegoEntity> findByPlataforma(String plataforma) {
		
		return this.juegoRepository.findByPlataformasContainingIgnoreCase(plataforma);
		
	}
	
	//Obtener las expansiones
	public List<JuegoEntity> findByExpansiones() {
		
		return this.juegoRepository.findByTipo(TipoJuego.EXPANSION);
		
	}
	
	//Obtener los DLCs
	public List<JuegoEntity> findByDlc() {
		
		return this.juegoRepository.findByTipo(TipoJuego.DLC);
		
	}
	
	//Obtener los juegos base
	public List<JuegoEntity> findByBase() {
		
		return this.juegoRepository.findByTipo(TipoJuego.BASE);
		
	}
	
	//Buscar juegos en un rango de precios
	public List<JuegoEntity> findByPrecio(double precioMin, double precioMax) {
		
		return this.juegoRepository.findByPrecioBetween(precioMin, precioMax);
		
	}
	
	//Buscar el top 5 de éxitos (top 5 de juegos más descargados)
	public List<JuegoEntity> findTop5() {
		
		return this.juegoRepository.findTop5ByOrderByDescargasDesc();
		
	}
	
	//Marcar o desmarcar juego como completado
	public JuegoEntity alternarCompletado(long id) {
		
		JuegoEntity juego = this.findById(id);
		
		boolean estado = juego.getCompletado() != null ? juego.getCompletado() : false;
		
		juego.setCompletado(!estado);
		
		return this.juegoRepository.save(juego);
		
	}
	
	//Reducir precio de todos los juegos de un género con un % indicado.
	public List<JuegoEntity> aplicarDescuento(String genero, double descuento) {
		
		List<JuegoEntity> juegos = this.juegoRepository.findByGeneroContainingIgnoreCase(genero);
		
		if(descuento > 1.0) {
			
			descuento /= 100.0;
			
		}
		
		for(JuegoEntity j : juegos) {
			
			double costeActual = j.getPrecio();
			
			double costeFinal = costeActual * (1.0 - descuento);
			
			if(costeFinal < 0) {
				
				costeFinal = 0.0;
				
			}
			
			j.setPrecio(costeFinal);
			
		}
		
		return this.juegoRepository.saveAll(juegos);
		
	}
	
	
}
